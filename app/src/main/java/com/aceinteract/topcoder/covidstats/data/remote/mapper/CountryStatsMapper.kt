package com.aceinteract.topcoder.covidstats.data.remote.mapper

import com.aceinteract.topcoder.covidstats.data.remote.models.CountryStatsRemote
import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryStatsMapper @Inject constructor() :
    RemoteModelMapper<CountryStatsRemote, CountryStatsEntity> {

    var lastUpdate: Date? = null

    override fun mapFromModel(model: CountryStatsRemote): CountryStatsEntity {
        return CountryStatsEntity(
            model.country,
            model.country_abbreviation,
            safeIntParse(model.total_cases),
            safeIntParse(model.new_cases),
            safeIntParse(model.total_deaths),
            safeIntParse(model.new_deaths),
            safeIntParse(model.total_recovered),
            safeIntParse(model.active_cases),
            safeIntParse(model.serious_critical),
            safeFloatParse(model.cases_per_mill_pop),
            model.flag,
            lastUpdate!!
        )
    }

}