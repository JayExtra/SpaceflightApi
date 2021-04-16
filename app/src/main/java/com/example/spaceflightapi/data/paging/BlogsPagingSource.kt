package com.example.spaceflightapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.models.BlogResponse
import retrofit2.HttpException
import java.io.IOException

private const val SPACEFLIGHT_STARTING_PAGE_INDEX = 1
class BlogsPagingSource(private val spaceFlightAPI: SpaceFlightAPI) : PagingSource<Int , BlogResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BlogResponse> {
        val position = params.key ?: SPACEFLIGHT_STARTING_PAGE_INDEX

        return try {
            val response = spaceFlightAPI.getBlogs()

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

    override fun getRefreshKey(state: PagingState<Int, BlogResponse>): Int? {
        return state.anchorPosition
    }
}