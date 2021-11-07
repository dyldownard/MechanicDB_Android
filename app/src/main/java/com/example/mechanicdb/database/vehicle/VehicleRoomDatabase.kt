package com.example.mechanicdb.database.vehicle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mechanicdb.models.Vehicle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class VehicleRoomDatabase : RoomDatabase() {
    abstract fun vehicleDao() : VehicleDao

    private class VehicleDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                database ->
                scope.launch {
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: VehicleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): VehicleRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VehicleRoomDatabase::class.java,
                    "vehicle_database"
                ).addCallback(VehicleDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}