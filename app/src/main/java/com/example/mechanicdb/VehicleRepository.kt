package com.example.mechanicdb

import androidx.annotation.WorkerThread
import com.example.mechanicdb.models.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleRepository(private val vehicleDao: VehicleDao) {

    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insertVehicle(vehicle)
    }

}