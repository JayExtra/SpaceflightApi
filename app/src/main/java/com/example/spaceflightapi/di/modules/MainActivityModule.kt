package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector( modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity


}