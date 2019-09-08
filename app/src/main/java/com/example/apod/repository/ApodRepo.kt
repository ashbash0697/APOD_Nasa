package com.example.apod.repository

import androidx.lifecycle.LiveData
import com.example.apod.entity.ApodDb
import org.joda.time.LocalDate

interface ApodRepo {
    fun getApods(now: LocalDate?, minusDays: LocalDate?)
}