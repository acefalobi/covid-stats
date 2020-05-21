package com.aceinteract.topcoder.covidstats.data.remote.source

import com.aceinteract.topcoder.covidstats.data.remote.api.CovidStatsAPI
import com.aceinteract.topcoder.covidstats.data.remote.mapper.GeneralStatsMapper
import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity
import com.aceinteract.topcoder.covidstats.data.repository.remote.IGeneralStatsRemote
import com.aceinteract.topcoder.covidstats.exception.SourceException
import javax.inject.Inject

class GeneralStatsRemoteSource @Inject constructor(
    private val covidStatsAPI: CovidStatsAPI,
    private val generalStatsMapper: GeneralStatsMapper
) : IGeneralStatsRemote {

    override suspend fun getGeneralStats(): GeneralStatsEntity {
        val response = covidStatsAPI.getGeneralStats()

        if (response.errors != null) throw SourceException(response.errors)

        if (response.status != "success" || response.data == null) throw SourceException(
            response.errors ?: response.detail ?: "There was an error fetching general stats."
        )

        return generalStatsMapper.mapFromModel(response.data)
    }

}