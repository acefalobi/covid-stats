package com.aceinteract.topcoder.covidstats.data.repository.remote

import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity

interface IGeneralStatsRemote {

    suspend fun getGeneralStats(): GeneralStatsEntity

}