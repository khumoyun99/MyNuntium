package com.example.mynuntium.databse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class,NewsLikeEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun newsDao():NewsDao
    abstract fun newsLikeDao():NewsLikeDao
}