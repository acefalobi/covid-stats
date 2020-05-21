package com.aceinteract.topcoder.covidstats.domain.repository

import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import kotlinx.coroutines.flow.Flow

interface ICountryStatsRepository {

    fun getCountryStats(shouldRefresh: Boolean = false, page: Int = 1, search: String? = null): Flow<CountryStatsSet>

}