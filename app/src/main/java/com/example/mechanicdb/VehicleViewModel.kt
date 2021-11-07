package com.example.mechanicdb

import androidx.lifecycle.*
import com.example.mechanicdb.models.Vehicle
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class VehicleViewModel(private val repository: VehicleRepository) : ViewModel() {

    val allVehicles: LiveData<List<Vehicle>> = repository.allVehicles.asLiveData()

    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        repository.insert(vehicle)
    }

    fun remove(vehicle: Vehicle) = viewModelScope.launch {
        repository.remove(vehicle)
    }
}

class VehicleViewModelFactory(private val repository: VehicleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehicleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}