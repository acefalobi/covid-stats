package com.aceinteract.topcoder.covidstats.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "COUNTRY_STATS")
data class CountryStatsLocal(
    @PrimaryKey val country: String,
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
    val lastUpdated: Long
)