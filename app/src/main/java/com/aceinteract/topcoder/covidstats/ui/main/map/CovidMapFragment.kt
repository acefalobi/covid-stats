package com.aceinteract.topcoder.covidstats.ui.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.databinding.CovidMapFragmentBinding
import com.aceinteract.topcoder.covidstats.di.ViewModelFactory
import com.aceinteract.topcoder.covidstats.domain.model.Country
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.ui.base.BaseFragment
import com.aceinteract.topcoder.covidstats.util.showBottomSheet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.ui.IconGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class CovidMapFragment : BaseFragment<CovidMapFragmentBinding, CovidMapViewModel>() {

    private lateinit var map: GoogleMap

    private var stats = emptyList<CountryStats>()

    private val countries by lazy {
        Gson().fromJson<List<Country>>(
            requireContext().assets.open("countries.json").bufferedReader().use { it.readText() },
            object : TypeToken<List<Country>>() {}.type
        )
    }

    /**
     * Factory view model factory.
     */
    @Inject
    override lateinit var viewModelFactory: ViewModelFactory<CovidMapViewModel>

    override fun setupViewModel(): CovidMapViewModel = obtainViewModel(viewModelFactory)

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CovidMapFragmentBinding = CovidMapFragmentBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        processCountryStats()
    }

    override fun setupUI() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            map = googleMap
            map.setOnMarkerClickListener { marker ->
                (requireActivity() as AppCompatActivity).showBottomSheet(stats.find { it.country == marker.tag }!!)
                return@setOnMarkerClickListener true
            }
        }
    }

    private fun processCountryStats() {
        viewModel?.loadCountryStats(object : ActionResult.FetchResult<CountryStatsSet> {
            override fun onFinished() {
                viewBinding?.buttonRefreshStats?.isEnabled = true
                val boundsBuilder = LatLngBounds.Builder()
                stats.forEach { stats ->
                    val country = countries.find { it.code == stats.countryAbbreviation }
                    country?.let {
                        val markerOptions = makeMarker(stats, country)
                        boundsBuilder.include(markerOptions.position)
                        map.addMarker(markerOptions).tag = stats.country
                    }
                }
                map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 128))
            }

            override fun onSuccess(result: CountryStatsSet) {
                stats = result.rows
            }

            override fun onError(error: Throwable) {
                showSnackBar(
                    viewBinding!!.root,
                    error.message ?: getString(R.string.error_fetching_data),
                    true,
                    Toast.LENGTH_LONG
                )
            }
        })
    }

    private fun makeMarker(stats: CountryStats, country: Country): MarkerOptions {
        val iconGenerator = IconGenerator(context)

        iconGenerator.setBackground(null)

        val markerView = View.inflate(activity, R.layout.country_marker, null)
        iconGenerator.setContentView(markerView)

        markerView.findViewById<TextView>(R.id.text_country_short).text = stats.countryAbbreviation

        return MarkerOptions().apply {
            position(LatLng(country.lat, country.long))
            icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(stats.countryAbbreviation)))
        }
    }
}