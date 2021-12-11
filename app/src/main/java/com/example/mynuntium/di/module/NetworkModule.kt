package com.example.mynuntium.di.module

import com.example.mynuntium.BuildConfig
import com.example.mynuntium.networking.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl():String = "https://newsapi.org/v2/everything/"


    @Provides
    @Singleton
    fun provideGsonConvertor():GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl:String,
        gsonConverterFactory: GsonConverterFactory,
        okkHttpClient: OkHttpClient
    ):Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okkHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



























}