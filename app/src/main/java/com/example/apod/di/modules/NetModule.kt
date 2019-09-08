package com.example.apod.di.modules

import com.example.apod.network.ApodService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule{

    private val BASE_URL = "https://api.nasa.gov/"

    @Provides
    @Singleton
    fun provideApiKey(): String{
        //TODO: get the api key it is there in mail
        return ""
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(apiKey: String): OkHttpClient{

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor ({chain ->
                    //TODO: MOVE to a seperate fun
                    val original = chain.request()
                    val url = original.url().newBuilder().addQueryParameter("api_key", apiKey).build()
                    val newRequest = original.newBuilder().url(url).build()
                    chain.proceed(newRequest)
                }).readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApodService(retrofit: Retrofit): ApodService = retrofit.create(ApodService::class.java)



}