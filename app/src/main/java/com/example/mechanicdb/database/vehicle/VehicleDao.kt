package com.example.mechanicdb.database.vehicle

import androidx.room.*
import com.example.mechanicdb.models.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert
    fun insertVehicle(vehicle: Vehicle)

    @Delete
    fun deleteVehicle(vehicle: Vehicle)

    @Update
    fun updateVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicle")
    fun getAll(): Flow<List<Vehicle>>

}