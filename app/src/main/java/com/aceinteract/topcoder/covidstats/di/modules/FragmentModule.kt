package com.aceinteract.topcoder.covidstats.di.modules

import com.aceinteract.topcoder.covidstats.ui.main.countrystats.CountryStatsFragment
import com.aceinteract.topcoder.covidstats.ui.main.generalstats.TotalStatsFragment
import com.aceinteract.topcoder.covidstats.ui.main.map.CovidMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@Module
abstract class FragmentModule {

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ContributesAndroidInjector
    internal abstract fun contributeTotalStatsFragment(): TotalStatsFragment

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ContributesAndroidInjector
    internal abstract fun contributeCountryStatsFragment(): CountryStatsFragment

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ContributesAndroidInjector
    internal abstract fun contributeCovidMapFragment(): CovidMapFragment

}