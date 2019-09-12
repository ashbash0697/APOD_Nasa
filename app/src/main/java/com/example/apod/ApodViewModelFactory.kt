package com.example.apod

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apod.di.components.ApodComponent

class ApodViewModelFactory(val application: APODApllication): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val t: T= super.create(modelClass)
        if (t is ApodComponent.Injectable) {

            t.inject(application.apodComponent)
        }
        return t
    }
}