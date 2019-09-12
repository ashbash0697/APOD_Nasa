package com.example.apod.di.components


import com.example.apod.ApodBoundaryCallback
import com.example.apod.apoddetail.ApodDtlViewModel
import com.example.apod.main.MainActivityViewModel
import com.example.apod.di.modules.AppModule
import com.example.apod.di.modules.DbModule
import com.example.apod.di.modules.NetModule
import com.example.apod.di.modules.RepoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetModule::class, AppModule::class, DbModule::class, RepoModule::class))
interface ApodComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(apodBoundaryCallback: ApodBoundaryCallback)

    fun inject(apodDtlViewModel: ApodDtlViewModel)

    interface Injectable{
        fun inject(apodComponent: ApodComponent)
    }

}