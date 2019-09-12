package com.example.apod.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.apod.entity.ApodDb
import org.joda.time.LocalDate

interface ApodRepo {
    fun getApods(now: LocalDate?, minusDays: LocalDate?): LiveData<List<ApodDb>>
    fun getPagedApods(): DataSource.Factory<Int, ApodDb>
}