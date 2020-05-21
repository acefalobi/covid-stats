package com.aceinteract.topcoder.covidstats.ui.main.generalstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.databinding.TotalStatsFragmentBinding
import com.aceinteract.topcoder.covidstats.di.ViewModelFactory
import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.ui.base.BaseFragment
import com.aceinteract.topcoder.covidstats.util.Constants
import com.aceinteract.topcoder.covidstats.util.getColorCompat
import com.aceinteract.topcoder.covidstats.view.PieChartView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class TotalStatsFragment : BaseFragment<TotalStatsFragmentBinding, TotalStatsViewModel>() {

    /**
     * Factory view model factory.
     */
    @Inject
    override lateinit var viewModelFactory: ViewModelFactory<TotalStatsViewModel>


    override fun setupViewModel(): TotalStatsViewModel = obtainViewModel(viewModelFactory)

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TotalStatsFragmentBinding = TotalStatsFragmentBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presentGeneralStats()
    }

    override fun setupUI() {

        viewBinding?.buttonRefreshStats?.setOnClickListener {
            presentGeneralStats()
        }

    }

    private fun presentGeneralStats() {
        viewBinding?.buttonRefreshStats?.isEnabled = false

        viewModel?.loadGeneralStats(true, object : ActionResult.FetchResult<GeneralStats> {
            override fun onSuccess(result: GeneralStats) {
                viewBinding?.layoutLoader?.visibility = View.GONE
                viewBinding?.layoutContent?.visibility = View.VISIBLE
                viewBinding?.buttonRefreshStats?.isEnabled = true
                viewBinding?.textLastUpdated?.text = SimpleDateFormat(
                    Constants.DATE_FORMAT,
                    Locale.getDefault()
                ).format(result.lastUpdate)
                viewBinding?.textTotalCases?.text = "%,d".format(result.totalCases)
                viewBinding?.textInfected?.text = "%,d".format(result.currentlyInfected)
                viewBinding?.textRecovered?.text = "%,d".format(result.recoveryCases)
                viewBinding?.textDeaths?.text = "%,d".format(result.deathCases)

                viewBinding?.pieGeneralStats?.piePieces = listOf(
                    PieChartView.PiePiece(
                        result.currentlyInfected.toDouble(),
                        requireContext().getColorCompat(R.color.color_warning)
                    ),
                    PieChartView.PiePiece(
                        result.recoveryCases.toDouble(),
                        requireContext().getColorCompat(R.color.color_good)
                    ),
                    PieChartView.PiePiece(
                        result.deathCases.toDouble(),
                        requireContext().getColorCompat(R.color.color_danger)
                    )
                )
            }

            override fun onError(error: Throwable) {
                viewBinding?.buttonRefreshStats?.isEnabled = true
                showSnackBar(
                    viewBinding!!.root,
                    error.message ?: getString(R.string.error_fetching_data),
                    true,
                    Toast.LENGTH_LONG
                )
            }
        })
    }

}
