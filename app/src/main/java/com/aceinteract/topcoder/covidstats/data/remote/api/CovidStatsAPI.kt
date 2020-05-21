package com.aceinteract.topcoder.covidstats.data.remote.api

import com.aceinteract.topcoder.covidstats.data.remote.models.BaseRemoteModel
import com.aceinteract.topcoder.covidstats.data.remote.models.CountryStatsResponse
import com.aceinteract.topcoder.covidstats.data.remote.models.GeneralStatsRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidStatsAPI {

    @GET("cases/general-stats")
    suspend fun getGeneralStats(): BaseRemoteModel<GeneralStatsRemote>

    @GET("cases/countries-search")
    suspend fun getCountryStats(
        @Query("page") page: Int = 1,
        @Query("search") search: String? = null,
        @Query("order") order: String = "total_cases",
        @Query("how") how: String = "desc"
    ): BaseRemoteModel<CountryStatsResponse>

}