package com.example.mynuntium.di.module

import android.content.Context
import androidx.room.Room
import com.example.mynuntium.databse.AppDatabase
import com.example.mynuntium.databse.NewsDao
import com.example.mynuntium.databse.NewsLikeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext():Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase):NewsDao = appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideNewsLikeDao(appDatabase: AppDatabase):NewsLikeDao = appDatabase.newsLikeDao()



}