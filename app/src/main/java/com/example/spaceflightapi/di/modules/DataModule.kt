package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.api.AgenciesAPI
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.data.repository.AgenciesRepository
import com.example.spaceflightapi.data.repository.SpaceNewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    fun provideSpaceNewsRepository(spaceFlightAPI: SpaceFlightAPI) : SpaceNewsRepository =
            SpaceNewsRepository(spaceFlightAPI)

    @Singleton
    @Provides
    fun provideAgenciesRepository(agenciesAPI: AgenciesAPI) : AgenciesRepository =
        AgenciesRepository(agenciesAPI)
}