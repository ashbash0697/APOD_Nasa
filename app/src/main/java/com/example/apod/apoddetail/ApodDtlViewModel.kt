package com.example.apod.apoddetail

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.apod.ApodBoundaryCallback
import com.example.apod.di.components.ApodComponent
import com.example.apod.entity.ApodDb
import com.example.apod.repository.ApodRepo
import javax.inject.Inject

class ApodDtlViewModel : ViewModel(), ApodComponent.Injectable{
    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }

    @Inject
    lateinit var repo: ApodRepo

    fun getPagedApods(config: PagedList.Config): LivePagedListBuilder<Int, ApodDb> {
        val livePagedListBuilder = LivePagedListBuilder<Int, ApodDb>(
            repo.getPagedApods(),
            config
        )
        livePagedListBuilder.setBoundaryCallback(ApodBoundaryCallback())
        return livePagedListBuilder
    }

}