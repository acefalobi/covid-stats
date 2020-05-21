package com.aceinteract.topcoder.covidstats.domain.model

data class PaginationMeta(
    val currentPage: Int,
    val currentPageSize: Int,
    val totalPages: Int,
    val totalRecords: Int
)