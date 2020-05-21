package com.aceinteract.topcoder.covidstats.domain.usecases.generalstats

import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import com.aceinteract.topcoder.covidstats.domain.repository.IGeneralStatsRepository
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
class GetGeneralStats @Inject constructor(
    private val repository: IGeneralStatsRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetGeneralStats.Params, GeneralStats>(postExecutionThread) {

    override fun build(params: Params?): Flow<GeneralStats> {
        if (params == null) throw NoParamsException()
        return repository.getGeneralStats(params.refresh)
    }

    data class Params constructor(val refresh: Boolean) {
        companion object {
            fun make(refresh: Boolean): Params {
                return Params(
                    refresh
                )
            }
        }
    }

}