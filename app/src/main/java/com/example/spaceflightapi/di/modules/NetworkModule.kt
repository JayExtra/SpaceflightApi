package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.api.AgenciesAPI
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.utilities.Config
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesSpaceflightApi( @Named("news") retrofit: Retrofit) : SpaceFlightAPI {
        return retrofit.create(SpaceFlightAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesAgenciesApi( @Named("agencies") retrofit: Retrofit) : AgenciesAPI {
        return retrofit.create(AgenciesAPI::class.java)
    }

    @Singleton
    @Provides
    @Named("news")
    fun providesRetrofit():Retrofit =
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    @Named("agencies")
    fun providesAgenciesRetrofit():Retrofit =
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL_2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()





}