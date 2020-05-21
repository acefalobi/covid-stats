package com.aceinteract.topcoder.covidstats.di.modules

import android.content.Context
import androidx.room.Room
import com.aceinteract.topcoder.covidstats.data.local.room.AppDatabase
import com.aceinteract.topcoder.covidstats.data.local.room.dao.CountryStatsDao
import com.aceinteract.topcoder.covidstats.data.local.room.dao.GeneralStatsDao
import com.aceinteract.topcoder.covidstats.data.local.source.CountryStatsLocalSource
import com.aceinteract.topcoder.covidstats.data.local.source.GeneralStatsLocalSource
import com.aceinteract.topcoder.covidstats.data.repository.local.ICountryStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.local.IGeneralStatsLocal
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesGeneralStatsLocal(generalStatsLocal: GeneralStatsLocalSource): IGeneralStatsLocal {
        return generalStatsLocal
    }

    @Singleton
    @Provides
    fun providesCountryStatsLocal(countryStatsLocalSource: CountryStatsLocalSource): ICountryStatsLocal {
        return countryStatsLocalSource
    }

    @Singleton
    @Provides
    fun providesGeneralStatsDao(database: AppDatabase): GeneralStatsDao {
        return database.generalStatsDao()
    }

    @Singleton
    @Provides
    fun providesCountryStatsDao(database: AppDatabase): CountryStatsDao {
        return database.countryStatsDao()
    }

    @Singleton
    @Provides
    fun providesDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "covid_stats_db"
        ).fallbackToDestructiveMigration().build()
    }

}