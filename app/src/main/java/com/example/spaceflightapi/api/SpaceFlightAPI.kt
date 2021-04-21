package com.example.spaceflightapi.api

import com.example.spaceflightapi.models.AgenciesResponse
import com.example.spaceflightapi.models.ArticlesResponse
import com.example.spaceflightapi.models.BlogResponse
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceFlightAPI {

    @GET("articles")
    suspend fun  getArticles(
            @Query("_limit") limit : Int
    ): List<ArticlesResponse>

    @GET("blogs")
    suspend fun getBlogs(): List<BlogResponse>

}