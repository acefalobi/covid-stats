package com.aceinteract.topcoder.covidstats.data.local.mapper

import com.aceinteract.topcoder.covidstats.data.local.models.GeneralStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralStatsLocalMapper @Inject constructor() : LocalModelMapper<GeneralStatsLocal, GeneralStatsEntity> {

    override fun mapToEntity(model: GeneralStatsLocal): GeneralStatsEntity {
        return GeneralStatsEntity(
            model.totalCases,
            model.recoveryCases,
            model.deathCases,
            model.currentlyInfected,
            model.casesWithOutcome,
            model.mildConditionActiveCases,
            model.criticalConditionActiveCases,
            model.recoveredClosedCases,
            model.deathClosedCases,
            model.closedCasesRecoveredPercentage,
            model.closedCasesDeathPercentage,
            model.activeCasesMildPercentage,
            model.activeCasesCriticalPercentage,
            model.generalDeathRate,
            Date(model.lastUpdate)
        )
    }

    override fun mapToModel(entity: GeneralStatsEntity): GeneralStatsLocal {
        return GeneralStatsLocal(
            entity.totalCases,
            entity.recoveryCases,
            entity.deathCases,
            entity.currentlyInfected,
            entity.casesWithOutcome,
            entity.mildConditionActiveCases,
            entity.criticalConditionActiveCases,
            entity.recoveredClosedCases,
            entity.deathClosedCases,
            entity.closedCasesRecoveredPercentage,
            entity.closedCasesDeathPercentage,
            entity.activeCasesMildPercentage,
            entity.activeCasesCriticalPercentage,
            entity.generalDeathRate,
            entity.lastUpdate.time
        )
    }


}