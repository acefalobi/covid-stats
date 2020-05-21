package com.aceinteract.topcoder.covidstats.data.repository.mapper

import com.aceinteract.topcoder.covidstats.data.repository.model.GeneralStatsEntity
import com.aceinteract.topcoder.covidstats.domain.model.GeneralStats
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralStatsEntityMapper @Inject constructor() : EntityMapper<GeneralStatsEntity, GeneralStats> {

    override fun mapFromEntity(entity: GeneralStatsEntity): GeneralStats {
        return GeneralStats(
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
            entity.lastUpdate
        )
    }

    override fun mapToEntity(domain: GeneralStats): GeneralStatsEntity {
        return GeneralStatsEntity(
            domain.totalCases,
            domain.recoveryCases,
            domain.deathCases,
            domain.currentlyInfected,
            domain.casesWithOutcome,
            domain.mildConditionActiveCases,
            domain.criticalConditionActiveCases,
            domain.recoveredClosedCases,
            domain.deathClosedCases,
            domain.closedCasesRecoveredPercentage,
            domain.closedCasesDeathPercentage,
            domain.activeCasesMildPercentage,
            domain.activeCasesCriticalPercentage,
            domain.generalDeathRate,
            domain.lastUpdate
        )
    }

}