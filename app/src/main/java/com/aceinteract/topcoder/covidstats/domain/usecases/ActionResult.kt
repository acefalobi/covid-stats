package com.aceinteract.topcoder.covidstats.domain.usecases

sealed class ActionResult {

    interface FetchResult<T> {

        fun onFinished() {}

        fun onSuccess(result: T)

        fun onError(error: Throwable)
    }

}