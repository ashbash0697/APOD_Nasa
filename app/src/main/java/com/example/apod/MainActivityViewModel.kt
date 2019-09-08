package com.example.apod


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apod.di.components.ApodComponent
import com.example.apod.entity.ApodDb
import com.example.apod.repository.ApodRepo
import org.joda.time.LocalDate
import javax.inject.Inject

class MainActivityViewModel() : ViewModel(), ApodComponent.Injectable{

    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }

    @Inject
    lateinit var repo: ApodRepo

    fun getApods() {
        return repo.getApods(LocalDate.now(), LocalDate.now().minusDays(20))
    }

}