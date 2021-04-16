package com.example.spaceflightapi.api

import com.example.spaceflightapi.models.ArticlesResponse
import com.example.spaceflightapi.models.BlogResponse
import dagger.Provides
import retrofit2.http.GET

interface SpaceFlightAPI {

    @GET("articles")
    suspend fun  getArticles(): List<ArticlesResponse>

    @GET("blogs")
    suspend fun getBlogs(): List<BlogResponse>


}