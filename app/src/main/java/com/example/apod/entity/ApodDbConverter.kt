package com.example.apod.entity

import androidx.room.TypeConverter
import org.joda.time.LocalDate

object ApodDbConverter{

    @TypeConverter
    @JvmStatic
    fun fromTimeStamp(value: Long) : LocalDate {return LocalDate(value)}

    @TypeConverter
    @JvmStatic
    fun  toTimeStamp(localDate: LocalDate): Long{
        return localDate.toDateTimeAtStartOfDay().millis
    }
}