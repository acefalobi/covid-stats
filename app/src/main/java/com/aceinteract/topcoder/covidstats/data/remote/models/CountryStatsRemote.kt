package com.aceinteract.topcoder.covidstats.data.remote.models

data class CountryStatsRemote(
    val country: String,
    val country_abbreviation: String,
    val total_cases: String,
    val new_cases: String,
    val total_deaths: String,
    val new_deaths: String,
    val total_recovered: String,
    val active_cases: String,
    val serious_critical: String,
    val cases_per_mill_pop: String,
    val flag: String
)