package com.aceinteract.topcoder.covidstats.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aceinteract.topcoder.covidstats.data.local.models.CountryStatsLocal
import com.aceinteract.topcoder.covidstats.data.local.models.GeneralStatsLocal
import com.aceinteract.topcoder.covidstats.data.local.room.dao.CountryStatsDao
import com.aceinteract.topcoder.covidstats.data.local.room.dao.GeneralStatsDao

@Database(
    entities = [GeneralStatsLocal::class, CountryStatsLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun generalStatsDao(): GeneralStatsDao

    abstract fun countryStatsDao(): CountryStatsDao

}