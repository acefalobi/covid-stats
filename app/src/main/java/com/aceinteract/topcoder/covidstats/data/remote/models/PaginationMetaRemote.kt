package com.aceinteract.topcoder.covidstats.data.remote.models

data class PaginationMetaRemote(
    val currentPage: Int,
    val currentPageSize: Int,
    val totalPages: Int,
    val totalRecords: Int
)