package com.aceinteract.topcoder.covidstats.data.local.mapper

import com.aceinteract.topcoder.covidstats.data.local.models.CountryStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryStatsLocalMapper @Inject constructor() :
    LocalModelMapper<CountryStatsLocal, CountryStatsEntity> {

    override fun mapToModel(entity: CountryStatsEntity): CountryStatsLocal {
        return CountryStatsLocal(
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
            entity.lastUpdate.time
        )
    }


    override fun mapToEntity(model: CountryStatsLocal): CountryStatsEntity {
        return CountryStatsEntity(
            model.country,
            model.countryAbbreviation,
            model.totalCases,
            model.newCases,
            model.totalDeaths,
            model.newDeaths,
            model.totalRecovered,
            model.activeCases,
            model.seriousCritical,
            model.casesPerMillPop,
            model.flag,
            Date(model.lastUpdated)
        )
    }

}