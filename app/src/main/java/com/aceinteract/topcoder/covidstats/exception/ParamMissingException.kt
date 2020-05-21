package com.aceinteract.topcoder.covidstats.exception

class ParamMissingException(
    parameterName: String,
    errorMessage: String = "You need parameter: \"$parameterName\" for this use case"
) : Exception(errorMessage)