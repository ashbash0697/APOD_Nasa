package com.example.apod.database



import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apod.entity.ApodDb
import com.example.apod.entity.ApodDbConverter

@Database(entities = arrayOf(ApodDb::class), version = 1, exportSchema = false)
@TypeConverters(ApodDbConverter::class)
abstract class ApodDatabase : RoomDatabase(){
    abstract fun apodDao(): ApodDao
}