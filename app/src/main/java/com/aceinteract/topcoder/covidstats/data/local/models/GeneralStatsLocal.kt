package com.aceinteract.topcoder.covidstats.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENERAL_STATS")
data class GeneralStatsLocal(
    val totalCases: Int,
    val recoveryCases: Int,
    val deathCases: Int,
    val currentlyInfected: Int,
    val casesWithOutcome: Int,
    val mildConditionActiveCases: Int,
    val criticalConditionActiveCases: Int,
    val recoveredClosedCases: Int,
    val deathClosedCases: Int,
    val closedCasesRecoveredPercentage: Float,
    val closedCasesDeathPercentage: Float,
    val activeCasesMildPercentage: Float,
    val activeCasesCriticalPercentage: Float,
    val generalDeathRate: Float,
    val lastUpdate: Long,
    @PrimaryKey var id: Int = 0
)