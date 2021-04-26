package com.example.spaceflightapi.viewmodels

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.spaceflightapi.data.repository.AgenciesRepository
import com.example.spaceflightapi.data.repository.SpaceNewsRepository
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(private val agenciesRepository: AgenciesRepository) : ViewModel(){


private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val agency = currentQuery.switchMap { queryString ->
        agenciesRepository.getAgencies(queryString).cachedIn(viewModelScope)
    }

    fun getAgencies(query: String){
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "space"
    }

}