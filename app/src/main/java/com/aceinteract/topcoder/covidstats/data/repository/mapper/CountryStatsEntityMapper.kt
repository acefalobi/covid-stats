package com.aceinteract.topcoder.covidstats.data.repository.mapper

import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsEntity
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryStatsEntityMapper @Inject constructor() :
    EntityMapper<CountryStatsEntity, CountryStats> {

    override fun mapFromEntity(entity: CountryStatsEntity): CountryStats {
        return CountryStats(
            entity.country,
            entity.countryAbbreviation,
            entity.totalCases,
            entity.newCases,
            entity.totalDeaths,
            entity.newDeaths,
            entity.totalRecovered,
            entity.activeCases,
            entity.seriousCritical,
            entity.casesPerMillPop,
            entity.flag,
            entity.lastUpdate
        )
    }

    override fun mapToEntity(domain: CountryStats): CountryStatsEntity {
        return CountryStatsEntity(
            domain.country,
            domain.countryAbbreviation,
            domain.totalCases,
            domain.newCases,
            domain.totalDeaths,
            domain.newDeaths,
            domain.totalRecovered,
            domain.activeCases,
            domain.seriousCritical,
            domain.casesPerMillPop,
            domain.flag,
            domain.lastUpdate
        )
    }

}