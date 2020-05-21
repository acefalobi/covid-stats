package com.aceinteract.topcoder.covidstats.ui.main.countrystats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.domain.usecases.countrystats.FilterCountryStats
import com.aceinteract.topcoder.covidstats.domain.usecases.countrystats.GetCountryStats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class CountryStatsViewModel @Inject constructor(
    private var getCountryStats: GetCountryStats,
    private var filterCountryStats: FilterCountryStats
) : ViewModel() {

    fun filterCountryStats(query: String, actionResult: ActionResult.FetchResult<List<CountryStats>>) {
        viewModelScope.launch {
            filterCountryStats.executeAndPerformAction(GetCountryStats.Params.make(false, 1, query),
                object : ActionResult.FetchResult<CountryStatsSet> {
                    override fun onSuccess(result: CountryStatsSet) {
                        actionResult.onSuccess(result.rows)
                    }

                    override fun onError(error: Throwable) {
                        actionResult.onError(error)
                    }
                })
        }
    }

    fun loadCountryStats(
        shouldRefresh: Boolean,
        actionResult: ActionResult.FetchResult<CountryStatsSet>,
        page: Int = 1
    ) {
        viewModelScope.launch {
            getCountryStats.executeAndPerformAction(
                GetCountryStats.Params.make(shouldRefresh, page),
                object : ActionResult.FetchResult<CountryStatsSet> {
                    override fun onSuccess(result: CountryStatsSet) {
                        actionResult.onSuccess(result)
                        if (result.paginationMeta.currentPage != result.paginationMeta.totalPages) {
                            loadCountryStats(true, actionResult, page + 1)
                        } else actionResult.onFinished()
                    }

                    override fun onError(error: Throwable) {
                        actionResult.onFinished()
                    }
                }
            )
        }
    }

}
