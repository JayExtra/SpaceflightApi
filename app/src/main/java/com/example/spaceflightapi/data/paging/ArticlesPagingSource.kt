package com.example.spaceflightapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.models.ArticlesResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val SPACEFLIGHT_STARTING_PAGE_INDEX = 1
private const val RESULT_LIMIT = 40
class ArticlesPagingSource( private val spaceFlightAPI: SpaceFlightAPI) : PagingSource<Int, ArticlesResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesResponse> {

        val position = params.key ?: SPACEFLIGHT_STARTING_PAGE_INDEX

        return try {
            val response = spaceFlightAPI.getArticles(RESULT_LIMIT)

            LoadResult.Page(
                    data = response ,
                    prevKey = if(position == SPACEFLIGHT_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if(response.isEmpty()) null else position + 1
            )

        }catch (exception : IOException){
            LoadResult.Error(exception)

        } catch (exception : HttpException){
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesResponse>): Int? {
        return state.anchorPosition
    }

}