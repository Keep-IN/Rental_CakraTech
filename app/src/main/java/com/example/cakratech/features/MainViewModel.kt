package com.example.cakratech.features

import androidx.lifecycle.ViewModel
import com.example.core.data.repositories.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {
    fun getCarData() = dataRepository.getCarData()
    fun getLocalData() = dataRepository.getAllBookings()
}