package com.aceinteract.topcoder.covidstats.di.modules

import com.aceinteract.topcoder.covidstats.data.repository.source.CountryStatsRepositoryImpl
import com.aceinteract.topcoder.covidstats.data.repository.source.GeneralStatsRepositoryImpl
import com.aceinteract.topcoder.covidstats.domain.repository.ICountryStatsRepository
import com.aceinteract.topcoder.covidstats.domain.repository.IGeneralStatsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsGeneralStatsModule(repository: GeneralStatsRepositoryImpl): IGeneralStatsRepository

    @Binds
    abstract fun bindsCountryStatsModule(repository: CountryStatsRepositoryImpl): ICountryStatsRepository

}