package com.example.mechanicdb.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName= "vehicle")
data class Vehicle(
    @ColumnInfo(name= "make")   var make: String,
    @ColumnInfo(name= "model")  var model: String,
    @ColumnInfo(name= "year")   var year: String,
    @ColumnInfo(name= "mileage")var milage: String?,
    @ColumnInfo(name= "type")   var type: Int,
    @PrimaryKey(autoGenerate = true) var vid: Int,
    @ColumnInfo(name= "VIN")       var VIN: String?
) : Parcelable
