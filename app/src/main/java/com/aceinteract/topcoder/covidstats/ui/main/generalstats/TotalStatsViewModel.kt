package com.aceinteract.topcoder.covidstats.ui.main.generalstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import com.aceinteract.topcoder.covidstats.domain.usecases.ActionResult
import com.aceinteract.topcoder.covidstats.domain.usecases.generalstats.GetGeneralStats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class TotalStatsViewModel @Inject constructor(
    private var getGeneralStats: GetGeneralStats
) : ViewModel() {

    fun loadGeneralStats(shouldRefresh: Boolean, result: ActionResult.FetchResult<GeneralStats>) {
        viewModelScope.launch {
            getGeneralStats.executeAndPerformAction(GetGeneralStats.Params.make(shouldRefresh), result)
        }
    }

}