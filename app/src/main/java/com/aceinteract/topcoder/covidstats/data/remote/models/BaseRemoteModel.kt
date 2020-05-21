package com.aceinteract.topcoder.covidstats.data.remote.models

data class BaseRemoteModel<T> (
    val data: T?,
    val status: String,
    val errors: String?,
    val detail: String?
)