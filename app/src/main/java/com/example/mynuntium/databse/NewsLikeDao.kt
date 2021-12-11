package com.example.mynuntium.databse

import androidx.room.*

@Dao
interface NewsLikeDao {

    @Insert
    suspend fun addNewsLike(newsLikeEntity: NewsLikeEntity)

    @Query("select * from newslikeentity")
    suspend fun getAllLikeNews():List<NewsLikeEntity>

    @Delete
    suspend fun deleteLikeNews(newsLikeEntity: NewsLikeEntity)


}