package com.aceinteract.topcoder.covidstats.data.remote.mapper

import com.aceinteract.topcoder.covidstats.data.remote.models.GeneralStatsRemote
import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralStatsMapper @Inject constructor() :
    RemoteModelMapper<GeneralStatsRemote, GeneralStatsEntity> {

    override fun mapFromModel(model: GeneralStatsRemote): GeneralStatsEntity {
        return GeneralStatsEntity(
            model.total_cases.replace(",", "").toInt(),
            model.recovery_cases.replace(",", "").toInt(),
            model.death_cases.replace(",", "").toInt(),
            model.currently_infected.replace(",", "").toInt(),
            model.cases_with_outcome.replace(",", "").toInt(),
            model.mild_condition_active_cases.replace(",", "").toInt(),
            model.critical_condition_active_cases.replace(",", "").toInt(),
            model.recovered_closed_cases.replace(",", "").toInt(),
            model.death_closed_cases.replace(",", "").toInt(),
            model.closed_cases_recovered_percentage.replace(",", "").toFloat(),
            model.closed_cases_death_percentage.replace(",", "").toFloat(),
            model.active_cases_mild_percentage.replace(",", "").toFloat(),
            model.active_cases_critical_percentage.replace(",", "").toFloat(),
            model.general_death_rate.replace(",", "").toFloat(),
            safeParse(model.last_update)

        )
    }

}