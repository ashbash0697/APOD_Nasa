package com.example.apod.network

data class ApodApi(var date: String , var title: String ,
                   var url: String , var explanation: String ,
                   var hdurl: String? = null,
                   var media_type: String ,
                   var copyright: String? = null)