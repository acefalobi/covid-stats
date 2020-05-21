package com.aceinteract.topcoder.covidstats.domain.usecases.countrystats

import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.repository.ICountryStatsRepository
import com.aceinteract.topcoder.covidstats.domain.usecases.FlowUseCase
import com.aceinteract.topcoder.covidstats.domain.usecases.PostExecutionThread
import com.aceinteract.topcoder.covidstats.exception.NoParamsException
import com.aceinteract.topcoder.covidstats.exception.ParamMissingException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class FilterCountryStats @Inject constructor(
    private val repository: ICountryStatsRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetCountryStats.Params, CountryStatsSet>(postExecutionThread) {

    override fun build(params: GetCountryStats.Params?): Flow<CountryStatsSet> {
        if (params == null) throw NoParamsException()
        if (params.search == null) throw ParamMissingException("search")
        return repository.getCountryStats(params.shouldRefresh, params.page, params.search)
    }

}