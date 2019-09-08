package com.example.apod.di.modules

import com.example.apod.database.ApodDatabase
import com.example.apod.network.ApodService
import com.example.apod.repository.ApodRepo
import com.example.apod.repository.ApodRepoImpl
import dagger.Module
import dagger.Provides

@Module
class RepoModule{

    @Provides
    fun provideApodRepo(apodDatabase: ApodDatabase, apodService: ApodService): ApodRepo{
        return ApodRepoImpl(apodDatabase, apodService)
    }

}