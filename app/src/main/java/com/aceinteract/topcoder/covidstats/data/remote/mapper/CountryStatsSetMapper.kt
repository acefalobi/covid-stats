package com.aceinteract.topcoder.covidstats.data.remote.mapper

import com.aceinteract.topcoder.covidstats.data.remote.models.CountryStatsResponse
import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsSetEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryStatsSetMapper @Inject constructor(
    private val countryStatsMapper: CountryStatsMapper,
    private val paginationMetaMapper: PaginationMetaMapper
) : RemoteModelMapper<CountryStatsResponse, CountryStatsSetEntity> {

    override fun mapFromModel(model: CountryStatsResponse): CountryStatsSetEntity {
        countryStatsMapper.lastUpdate = safeParse(model.last_update)
            return CountryStatsSetEntity(
            safeParse(model.last_update),
            paginationMetaMapper.mapFromModel(model.paginationMeta),
            countryStatsMapper.mapModelList(model.rows)
        )
    }

}