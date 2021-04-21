package com.example.spaceflightapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.spaceflightapi.data.repository.SpaceNewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor( private val spaceNewsRepository: SpaceNewsRepository) : ViewModel() {

    fun getArticles() = spaceNewsRepository.getArticles().cachedIn(viewModelScope)

    fun getBlogs() = spaceNewsRepository.getBlogs().cachedIn(viewModelScope)

}