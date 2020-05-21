package com.aceinteract.topcoder.covidstats.domain.model

import java.util.*

data class CountryStatsSet(
    val lastUpdate: Date,
    val paginationMeta: PaginationMeta,
    val rows: List<CountryStats>
)