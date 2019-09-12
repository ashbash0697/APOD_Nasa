package com.example.apod.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {

    @GET("/planetary/apod")
    fun getApod(@Query("date") date: String, @Query("hd") hdImage: Boolean = false) : Single<ApodApi>

}