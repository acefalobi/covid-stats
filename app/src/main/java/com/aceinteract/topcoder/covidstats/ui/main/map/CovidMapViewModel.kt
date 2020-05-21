package com.aceinteract.topcoder.covidstats.ui.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.domain.usecases.countrystats.GetCountryStats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class CovidMapViewModel @Inject constructor(
    private var getCountryStats: GetCountryStats
) : ViewModel() {

    fun loadCountryStats(actionResult: ActionResult.FetchResult<CountryStatsSet>, page: Int = 1) {
        viewModelScope.launch {
            getCountryStats.executeAndPerformAction(
                GetCountryStats.Params.make(false, page),
                object : ActionResult.FetchResult<CountryStatsSet> {
                    override fun onSuccess(result: CountryStatsSet) {
                        actionResult.onSuccess(result)
                        if (result.paginationMeta.currentPage != result.paginationMeta.totalPages) {
                            loadCountryStats(actionResult,page + 1)
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