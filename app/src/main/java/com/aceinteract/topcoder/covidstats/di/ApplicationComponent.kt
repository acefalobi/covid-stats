package com.aceinteract.topcoder.covidstats.di

import android.content.Context
import com.aceinteract.topcoder.covidstats.CovidStatsApplication
import com.aceinteract.topcoder.covidstats.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Dagger application component injector.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        LocalModule::class,
        RemoteModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CovidStatsApplication> {

    /**
     * Factory interface for creating application component.
     */
    @Component.Factory
    interface Factory {
        /**
         * Initializes and returns new Dagger Application Component instance.
         */
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}