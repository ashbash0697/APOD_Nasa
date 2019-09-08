package com.example.apod.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.LocalDate

@Entity
data class ApodDb(@PrimaryKey var date: LocalDate = LocalDate.now(), var title: String,
                  var url: String, var explanation: String,
                  var hdurl: String? = null,
                  var media_type: String,
                  var copyright: String? = null)