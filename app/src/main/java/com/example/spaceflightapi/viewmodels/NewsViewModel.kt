package com.example.spaceflightapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.spaceflightapi.data.repository.Repository
import com.example.spaceflightapi.models.ArticlesResponse
import javax.inject.Inject

class NewsViewModel @Inject constructor( private val repository: Repository) : ViewModel() {

    fun getArticles() = repository.getArticles().cachedIn(viewModelScope)

    fun getBlogs() = repository.getBlogs().cachedIn(viewModelScope)

}