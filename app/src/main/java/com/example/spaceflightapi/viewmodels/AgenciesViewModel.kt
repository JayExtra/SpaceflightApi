package com.example.spaceflightapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.spaceflightapi.data.repository.AgenciesRepository
import com.example.spaceflightapi.data.repository.SpaceNewsRepository
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(private val agenciesRepository: AgenciesRepository) : ViewModel(){

    fun getAgencies() = agenciesRepository.getAgencies().cachedIn(viewModelScope)


}