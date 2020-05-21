package com.aceinteract.topcoder.covidstats.domain.model

import java.util.*

data class CountryStats(
    val country: String,
    val countryAbbreviation: String,
    val totalCases: Int,
    val newCases: Int,
    val totalDeaths: Int,
    val newDeaths: Int,
    val totalRecovered: Int,
    val activeCases: Int,
    val seriousCritical: Int,
    val casesPerMillPop: Float,
    val flag: String,
    val lastUpdate: Date
)