package com.aceinteract.topcoder.covidstats.data.repository.remote

import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsSetEntity

interface ICountryStatsRemote {

    suspend fun getCountryStats(page: Int = 1, search: String? = null): CountryStatsSetEntity

}