package com.example.mechanicdb

import androidx.annotation.WorkerThread
import com.example.mechanicdb.models.Vehicle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VehicleRepository(private val vehicleDao: VehicleDao, private val scope: CoroutineScope) {

    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(vehicle: Vehicle) {
        scope.launch { vehicleDao.insertVehicle(vehicle) }

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(vehicle: Vehicle) {
        scope.launch { vehicleDao.deleteVehicle(vehicle) }

    }

}