package com.example.spaceflightapi.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.data.paging.ArticlesPagingSource
import com.example.spaceflightapi.data.paging.BlogsPagingSource
import com.example.spaceflightapi.models.ArticlesResponse
import com.example.spaceflightapi.models.BlogResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SpaceNewsRepository @Inject constructor(private val spaceFlightAPI: SpaceFlightAPI) {

fun getArticles() =

        Pager(
                config = PagingConfig(
                        pageSize = 20,
                        maxSize = 60,
                        enablePlaceholders = false

                ),
                pagingSourceFactory = {ArticlesPagingSource(spaceFlightAPI)}

        ).liveData


    fun getBlogs() =
            Pager(
                    config = PagingConfig(
                            pageSize = 20 ,
                            maxSize = 60 ,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = {BlogsPagingSource(spaceFlightAPI)}
            ).liveData

}