package com.aceinteract.topcoder.covidstats.exception

class NoParamsException(errorMessage: String = "You need params for this use case") :
    Exception(errorMessage)