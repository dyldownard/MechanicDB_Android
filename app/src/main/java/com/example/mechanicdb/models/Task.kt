package com.example.mechanicdb.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName= "task")
data class Task (
    @PrimaryKey(autoGenerate = true)        var tid: Int,
    @ColumnInfo(name= "vid")                var vid: Int,
    @ColumnInfo(name= "name")               var name: String,
    @ColumnInfo(name= "completed_by")       var compby: String,
    @ColumnInfo(name= "desc")               var desc: String,
    @ColumnInfo(name= "odo")                var odo: String?,
    @ColumnInfo(name= "tprice")             var tprice: String,
    @ColumnInfo(name= "datecomp")           var datecomp: String,
    @ColumnInfo(name= "datestart")          var datestart: String,
): Parcelable