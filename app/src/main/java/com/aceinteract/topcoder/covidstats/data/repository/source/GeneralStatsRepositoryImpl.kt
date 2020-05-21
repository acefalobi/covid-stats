package com.aceinteract.topcoder.covidstats.data.repository.source

import com.aceinteract.topcoder.covidstats.data.repository.local.IGeneralStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.mapper.GeneralStatsEntityMapper
import com.aceinteract.topcoder.covidstats.data.repository.remote.IGeneralStatsRemote
import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import com.aceinteract.topcoder.covidstats.domain.repository.IGeneralStatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeneralStatsRepositoryImpl @Inject constructor(
    private val generalStatsRemote: IGeneralStatsRemote,
    private val generalStatsLocal: IGeneralStatsLocal,
    private val generalStatsEntityMapper: GeneralStatsEntityMapper
) : IGeneralStatsRepository {

    override fun getGeneralStats(shouldRefresh: Boolean): Flow<GeneralStats> = flow {
        val localData = generalStatsLocal.getGeneralStats()
        localData?.let { emit(generalStatsEntityMapper.mapFromEntity(it)) }

        if (shouldRefresh) {
            val remoteData = generalStatsRemote.getGeneralStats()
            generalStatsLocal.updateGeneralStats(remoteData)
            emit(generalStatsEntityMapper.mapFromEntity(remoteData))
        }
    }

}