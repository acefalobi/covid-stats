package com.aceinteract.topcoder.covidstats.di.modules

import com.aceinteract.topcoder.covidstats.BuildConfig
import com.aceinteract.topcoder.covidstats.data.remote.api.CovidStatsAPI
import com.aceinteract.topcoder.covidstats.data.remote.api.CovidStatsFactory
import com.aceinteract.topcoder.covidstats.data.remote.source.CountryStatsRemoteSource
import com.aceinteract.topcoder.covidstats.data.remote.source.GeneralStatsRemoteSource
import com.aceinteract.topcoder.covidstats.data.repository.remote.ICountryStatsRemote
import com.aceinteract.topcoder.covidstats.data.repository.remote.IGeneralStatsRemote
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Singleton
        @JvmStatic
        @Provides
        fun provideCovidStatService(): CovidStatsAPI {
            return CovidStatsFactory.makeService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsGeneralStatsRemote(remote: GeneralStatsRemoteSource): IGeneralStatsRemote

    @Binds
    abstract fun bindsCountryStatsRemote(remote: CountryStatsRemoteSource): ICountryStatsRemote

}