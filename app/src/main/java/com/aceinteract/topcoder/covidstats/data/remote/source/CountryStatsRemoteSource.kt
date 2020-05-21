package com.aceinteract.topcoder.covidstats.data.remote.source

import com.aceinteract.topcoder.covidstats.data.remote.api.CovidStatsAPI
import com.aceinteract.topcoder.covidstats.data.remote.mapper.CountryStatsSetMapper
import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsSetEntity
import com.aceinteract.topcoder.covidstats.data.repository.remote.ICountryStatsRemote
import com.aceinteract.topcoder.covidstats.exception.SourceException
import javax.inject.Inject

class CountryStatsRemoteSource @Inject constructor(
    private val covidStatsAPI: CovidStatsAPI,
    private val countryStatsSetMapper: CountryStatsSetMapper
) : ICountryStatsRemote {

    override suspend fun getCountryStats(
        page: Int,
        search: String?
    ): CountryStatsSetEntity {
        val response = covidStatsAPI.getCountryStats(page, search)

        if (response.errors != null) throw SourceException(response.errors)

        if (response.status != "success" || response.data == null) throw SourceException(
            response.errors ?: response.detail ?: "There was an error fetching general stats."
        )

        return countryStatsSetMapper.mapFromModel(response.data)
    }

}