package com.aceinteract.topcoder.covidstats.data.repository.model

data class PaginationMetaEntity(
    val currentPage: Int,
    val currentPageSize: Int,
    val totalPages: Int,
    val totalRecords: Int
)