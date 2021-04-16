package com.example.spaceflightapi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.spaceflightapi.data.repository.Repository
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
}