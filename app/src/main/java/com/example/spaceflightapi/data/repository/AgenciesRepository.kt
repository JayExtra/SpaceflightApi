package com.example.spaceflightapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.spaceflightapi.api.AgenciesAPI
import com.example.spaceflightapi.api.SpaceFlightAPI
import com.example.spaceflightapi.data.paging.AgenciesPagingSource
import javax.inject.Inject

class AgenciesRepository @Inject constructor(private val agenciesAPI: AgenciesAPI) {

    fun getAgencies(query: String) =
        Pager(
                config = PagingConfig(
                        pageSize = 20,
                        maxSize = 100,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = {AgenciesPagingSource(agenciesAPI , query)}
        ).liveData

}