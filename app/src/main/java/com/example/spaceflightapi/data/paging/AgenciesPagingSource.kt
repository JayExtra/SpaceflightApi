package com.example.spaceflightapi.data.paging


import android.util.Log
import androidx.paging.PagingDataAdapter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spaceflightapi.api.AgenciesAPI
import com.example.spaceflightapi.models.AgenciesList
import retrofit2.HttpException
import java.io.IOException

private const val AGENCIES_STARTING_PAGE_INDEX = 1
private const val FEATURED = true
private const val LIMIT = 15
private val TAG = "Paging Result:"

class AgenciesPagingSource(private val agenciesAPI: AgenciesAPI) : PagingSource<Int , AgenciesList>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AgenciesList> {

        val position = params.key ?: AGENCIES_STARTING_PAGE_INDEX

       return  try {

            val response = agenciesAPI.getAgencies(FEATURED , LIMIT)
            val agency = response.results

           //Log.d(TAG, "load: ${agency}")

            LoadResult.Page(
                    data = agency ,
                    prevKey = if(position == AGENCIES_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if(agency.isEmpty()) null else position + 1
            )


        }catch (exception : IOException){
            LoadResult.Error(exception)
        }catch (httpException : HttpException){
            LoadResult.Error(httpException)
        }

    }
    override fun getRefreshKey(state: PagingState<Int, AgenciesList>): Int? {
        return state.anchorPosition
    }
}