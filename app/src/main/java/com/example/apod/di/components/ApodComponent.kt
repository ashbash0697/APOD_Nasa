package com.example.apod.di.components


import com.example.apod.MainActivityViewModel
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

    interface Injectable{
        fun inject(apodComponent: ApodComponent)
    }

}