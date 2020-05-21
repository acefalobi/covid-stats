package com.aceinteract.topcoder.covidstats.data.repository.mapper

import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsSetEntity
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryStatsSetEntityMapper @Inject constructor(
    private val countryStatsEntityMapper: CountryStatsEntityMapper,
    private val paginationMetaEntityMapper: PaginationMetaEntityMapper
) : EntityMapper<CountryStatsSetEntity, CountryStatsSet> {

    override fun mapFromEntity(entity: CountryStatsSetEntity): CountryStatsSet {
        return CountryStatsSet(
            entity.lastUpdate,
            paginationMetaEntityMapper.mapFromEntity(entity.paginationMeta),
            countryStatsEntityMapper.mapFromEntityList(entity.rows)
        )
    }

    override fun mapToEntity(domain: CountryStatsSet): CountryStatsSetEntity {
        return CountryStatsSetEntity(
            domain.lastUpdate,
            paginationMetaEntityMapper.mapToEntity(domain.paginationMeta),
            countryStatsEntityMapper.mapFromDomainList(domain.rows)
        )
    }

}