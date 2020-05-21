package com.aceinteract.topcoder.covidstats.domain.model

import java.util.*

data class GeneralStats(
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
    val lastUpdate: Date
)