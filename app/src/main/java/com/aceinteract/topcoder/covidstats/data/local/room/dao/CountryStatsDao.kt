package com.aceinteract.topcoder.covidstats.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aceinteract.topcoder.covidstats.data.local.models.CountryStatsLocal

@Dao
interface CountryStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryStats(stats: List<CountryStatsLocal>)

    @Query("SELECT * FROM COUNTRY_STATS ORDER BY totalCases DESC")
    suspend fun getCountryStats(): List<CountryStatsLocal>

    @Query("SELECT * FROM COUNTRY_STATS WHERE country LIKE :query ORDER BY totalCases DESC")
    suspend fun getCountryStats(query: String): List<CountryStatsLocal>
}