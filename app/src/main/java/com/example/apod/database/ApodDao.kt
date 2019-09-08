package com.example.apod.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apod.entity.ApodDb
import org.joda.time.LocalDate
import java.util.*

@Dao
interface ApodDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addApod(apodDb: ApodDb)

    @Query("Select * from ApodDb where date = :date")
    fun getApod(date: LocalDate): ApodDb

    @Query("Select * from ApodDb")
    fun getApod(): LiveData<List<ApodDb>>

}