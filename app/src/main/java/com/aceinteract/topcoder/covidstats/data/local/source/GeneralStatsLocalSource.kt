package com.aceinteract.topcoder.covidstats.data.local.source

import com.aceinteract.topcoder.covidstats.data.local.mapper.GeneralStatsLocalMapper
import com.aceinteract.topcoder.covidstats.data.local.room.dao.GeneralStatsDao
import com.aceinteract.topcoder.covidstats.data.repository.local.IGeneralStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity
import javax.inject.Inject

class GeneralStatsLocalSource @Inject constructor(
    private val generalStatsDao: GeneralStatsDao,
    private val generalStatsMapper: GeneralStatsLocalMapper
) : IGeneralStatsLocal {

    override suspend fun getGeneralStats(): GeneralStatsEntity? {
        return generalStatsDao.getGeneralStats()?.let {
            generalStatsMapper.mapToEntity(it)
        }
    }

    override suspend fun updateGeneralStats(stats: GeneralStatsEntity) {
        return generalStatsDao.updateGeneralStats(generalStatsMapper.mapToModel(stats))
    }


}