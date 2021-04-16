package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.data.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    fun provideRepository(spaceFlightAPI: SpaceFlightAPI) : Repository =
            Repository(spaceFlightAPI)
}