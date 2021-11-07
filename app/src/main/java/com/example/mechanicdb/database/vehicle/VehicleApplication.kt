package com.example.mechanicdb.database.vehicle

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class VehicleApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        VehicleRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        VehicleRepository(database.vehicleDao(), applicationScope)
    }
}