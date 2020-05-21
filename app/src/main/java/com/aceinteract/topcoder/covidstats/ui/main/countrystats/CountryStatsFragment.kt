package com.aceinteract.topcoder.covidstats.ui.main.countrystats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.databinding.CountryStatsFragmentBinding
import com.aceinteract.topcoder.covidstats.di.ViewModelFactory
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.ui.base.BaseFragment
import com.aceinteract.topcoder.covidstats.util.showBottomSheet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class CountryStatsFragment : BaseFragment<CountryStatsFragmentBinding, CountryStatsViewModel>() {

    /**
     * Factory view model factory.
     */
    @Inject
    override lateinit var viewModelFactory: ViewModelFactory<CountryStatsViewModel>

    private val countryStatsListAdapter = CountryStatsListAdapter {
        (requireActivity() as AppCompatActivity).showBottomSheet(it)
    }

    override fun setupViewModel(): CountryStatsViewModel = obtainViewModel(viewModelFactory)

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CountryStatsFragmentBinding = CountryStatsFragmentBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        processCountryStats(false)
    }

    override fun setupUI() {
        viewBinding?.run {
            recyclerCountryStats.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recyclerCountryStats.adapter = countryStatsListAdapter

            buttonRefreshStats.setOnClickListener {
                viewBinding?.layoutLoader?.visibility = View.VISIBLE
                viewBinding?.recyclerCountryStats?.visibility = View.GONE
                countryStatsListAdapter.submitList(emptyList())
                processCountryStats(true)
            }

            editSearchOrigin.addTextChangedListener {
                processCountryStatsFilter(it.toString())
            }
        }

    }

    private fun processCountryStatsFilter(query: String) {
        viewModel?.filterCountryStats(query, object : ActionResult.FetchResult<List<CountryStats>> {
            override fun onSuccess(result: List<CountryStats>) {
                countryStatsListAdapter.submitList(result)
                viewBinding?.recyclerCountryStats?.smoothScrollToPosition(0)
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

    private fun processCountryStats(shouldRefresh: Boolean) {
        viewBinding?.buttonRefreshStats?.isEnabled = false

        var newStats = emptyList<CountryStats>()

        viewModel?.loadCountryStats(
            shouldRefresh,
            object : ActionResult.FetchResult<CountryStatsSet> {
                override fun onFinished() {
                    viewBinding?.buttonRefreshStats?.isEnabled = true
                    viewBinding?.layoutLoader?.visibility = View.GONE
                    viewBinding?.recyclerCountryStats?.visibility = View.VISIBLE
                    countryStatsListAdapter.submitList(newStats)
                }

                override fun onSuccess(result: CountryStatsSet) {
                    viewBinding?.textProgressStatus?.text = getString(
                        R.string.status_fetching_region_stats_detailed,
                        result.paginationMeta.currentPage * 10,
                        result.paginationMeta.totalRecords
                    )
                    newStats = result.rows
                }

                override fun onError(error: Throwable) {
                    viewBinding?.buttonRefreshStats?.isEnabled = true
                    viewBinding?.layoutLoader?.visibility = View.GONE
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
