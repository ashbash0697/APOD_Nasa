package com.example.apod.di.modules

import android.app.Application
import androidx.room.Room
import com.example.apod.database.ApodDao
import com.example.apod.database.ApodDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule{

    @Provides
    @Singleton
    fun provideDatabase(context: Application): ApodDatabase{
        return Room.databaseBuilder(context, ApodDatabase::class.java, "apod.db").build()
    }

    fun provideApodDao(apodDatabase: ApodDatabase): ApodDao{
        return apodDatabase.apodDao()
    }
}