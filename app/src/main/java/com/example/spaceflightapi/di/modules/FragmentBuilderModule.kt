package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.ui.AgenciesFragment
import com.example.spaceflightapi.ui.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeAgenciesFragment() : AgenciesFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment() : NewsFragment


}