package com.aceinteract.topcoder.covidstats.data.repository.local

import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity

interface IGeneralStatsLocal {

    suspend fun getGeneralStats(): GeneralStatsEntity?

    suspend fun updateGeneralStats(stats: GeneralStatsEntity)

}