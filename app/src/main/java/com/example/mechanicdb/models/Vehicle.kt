package com.example.mechanicdb.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    var make: String,
    var model: String,
    var year: Int,
    var milage: Int,
    var image: String,
    var vid: Int,
    var VIN: Int
) : Parcelable