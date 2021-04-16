package com.example.spaceflightapi.di.modules

import dagger.Module

@Module(
        includes = [
    ViewModelModule::class,
    NetworkModule::class,
    DataModule::class
  ]
)
class AppModule