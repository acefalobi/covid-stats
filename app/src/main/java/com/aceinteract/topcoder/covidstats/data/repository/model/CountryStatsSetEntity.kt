package com.aceinteract.topcoder.covidstats.data.repository.model

import java.util.*

data class CountryStatsSetEntity(
    val lastUpdate: Date,
    val paginationMeta: PaginationMetaEntity,
    val rows: List<CountryStatsEntity>
)