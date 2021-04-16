package com.example.spaceflightapi.di.modules

import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.utilities.Config
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit =
            Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Singleton
    @Provides
    fun providesSpaceflightApi(retrofit: Retrofit) : SpaceFlightAPI {
        return retrofit.create(SpaceFlightAPI::class.java)
    }

}