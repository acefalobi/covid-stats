package com.aceinteract.topcoder.covidstats.data.repository.local

import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsEntity

interface ICountryStatsLocal {

    suspend fun getCountryStats(): List<CountryStatsEntity>

    suspend fun getCountryStats(query: String): List<CountryStatsEntity>

    suspend fun insertCountryStats(stats: List<CountryStatsEntity>)

}