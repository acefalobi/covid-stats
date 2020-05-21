package com.aceinteract.topcoder.covidstats.domain.usecases.countrystats

import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.repository.ICountryStatsRepository
import com.aceinteract.topcoder.covidstats.domain.usecases.FlowUseCase
import com.aceinteract.topcoder.covidstats.domain.usecases.PostExecutionThread
import com.aceinteract.topcoder.covidstats.exception.NoParamsException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class GetCountryStats @Inject constructor(
    private val repository: ICountryStatsRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetCountryStats.Params, CountryStatsSet>(postExecutionThread) {

    override fun build(params: Params?): Flow<CountryStatsSet> {
        if (params == null) throw NoParamsException()
        return repository.getCountryStats(params.shouldRefresh, params.page, params.search)
    }

    data class Params constructor(val shouldRefresh: Boolean, val page: Int, val search: String?) {
        companion object {
            fun make(shouldRefresh: Boolean, page: Int = 1, search: String? = null): Params {
                return Params(shouldRefresh, page, search)
            }
        }
    }

}