package com.example.mynuntium.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {

    @Insert
    suspend fun addNews(newsEntity: NewsEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addAllNews(list: List<NewsEntity>)

    @Update
    suspend fun updateNews(newsEntity: NewsEntity)

    @Query("select * from newsentity")
    suspend fun getAllNews():List<NewsEntity>

    @Query("select * from newsentity where isLike=:boo")
    suspend fun getAllLikelyNews(boo:Boolean):List<NewsEntity>

    @Query("select * from newsentity where type=:type")
    suspend fun getAllTypeNews(type:String):List<NewsEntity>


}