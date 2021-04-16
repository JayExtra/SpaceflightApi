package com.example.spaceflightapi

import com.example.spaceflightapi.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder()
        .application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}