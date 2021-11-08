package com.example.mechanicdb.models

import androidx.room.TypeConverter
import java.util.Date

class TimeConverter {
    companion object {
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time?.toLong()
        }
    }
}