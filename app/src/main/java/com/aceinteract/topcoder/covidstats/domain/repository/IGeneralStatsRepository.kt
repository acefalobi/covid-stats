package com.aceinteract.topcoder.covidstats.domain.repository

import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import kotlinx.coroutines.flow.Flow

interface IGeneralStatsRepository {

    fun getGeneralStats(shouldRefresh: Boolean): Flow<GeneralStats>

}