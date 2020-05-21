package com.aceinteract.topcoder.covidstats.di.modules

import com.aceinteract.topcoder.covidstats.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

}