package com.example.spaceflightapi.api

import com.example.spaceflightapi.models.AgenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AgenciesAPI {

    @GET("agencies")
    suspend fun getAgencies(
            @Query("featured")featured : Boolean,
            @Query("limit")limit:Int
    ): AgenciesResponse
}