package com.example.mechanicdb.database

import android.app.Application
import com.example.mechanicdb.database.task.TaskRepository
import com.example.mechanicdb.database.task.TaskRoomDatabase
import com.example.mechanicdb.database.vehicle.VehicleRepository
import com.example.mechanicdb.database.vehicle.VehicleRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MechanicApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val vehicle_database by lazy {
        VehicleRoomDatabase.getDatabase(this, applicationScope)
    }

    val vehicle_repository by lazy {
        VehicleRepository(vehicle_database.vehicleDao(), applicationScope)
    }

    val task_database by lazy {
        TaskRoomDatabase.getDatabase(this, applicationScope)
    }

    val task_repository by lazy {
        TaskRepository(task_database.taskDao(), applicationScope)
    }
}