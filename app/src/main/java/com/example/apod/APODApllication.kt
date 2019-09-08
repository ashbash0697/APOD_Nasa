package com.example.apod

import android.app.Application
import com.example.apod.di.components.ApodComponent
import com.example.apod.di.components.DaggerApodComponent
import com.example.apod.di.modules.AppModule
import dagger.internal.DaggerCollections
import net.danlew.android.joda.JodaTimeAndroid

class APODApllication: Application(){
    /*val apodComponent: ApodComponent by lazy {

    }*/

    val apodComponent : ApodComponent by lazy {
       DaggerApodComponent.builder()
           .appModule(AppModule(this))
           .build()
    }


    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}