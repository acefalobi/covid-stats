package com.aceinteract.topcoder.covidstats.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aceinteract.topcoder.covidstats.data.local.models.GeneralStatsLocal

@Dao
interface GeneralStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGeneralStats(stats: GeneralStatsLocal)

    @Query("SELECT * FROM GENERAL_STATS")
    suspend  fun getGeneralStats(): GeneralStatsLocal?
}