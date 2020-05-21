package com.aceinteract.topcoder.covidstats.data.remote.models

data class GeneralStatsRemote(
    val total_cases: String,
    val recovery_cases: String,
    val death_cases: String,
    val currently_infected: String,
    val cases_with_outcome: String,
    val mild_condition_active_cases: String,
    val critical_condition_active_cases: String,
    val recovered_closed_cases: String,
    val death_closed_cases: String,
    val closed_cases_recovered_percentage: String,
    val closed_cases_death_percentage: String,
    val active_cases_mild_percentage: String,
    val active_cases_critical_percentage: String,
    val general_death_rate: String,
    val last_update: String
)