package com.aceinteract.topcoder.covidstats.data.remote.models

data class CountryStatsResponse(
    val last_update: String,
    val paginationMeta: PaginationMetaRemote,
    val rows: List<CountryStatsRemote>
)