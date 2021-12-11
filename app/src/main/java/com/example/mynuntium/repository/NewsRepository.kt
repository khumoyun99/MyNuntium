package com.example.mynuntium.repository

import com.example.mynuntium.databse.NewsDao
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.databse.NewsLikeDao
import com.example.mynuntium.databse.NewsLikeEntity
import com.example.mynuntium.networking.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
    private val newsLikeDao: NewsLikeDao
) {

    suspend fun getAllNews(type: String) = flow {emit(apiService.getAllNewsApi(type))}

    suspend fun addAllNews(list:List<NewsEntity>) = newsDao.addAllNews(list)

    suspend fun getDbNews() = flow { emit(newsDao.getAllNews()) }

    suspend fun updateItemNews(newsEntity: NewsEntity) = newsDao.updateNews(newsEntity)

//    suspend fun getAllLikelyNews(boolean: Boolean) = flow { emit(newsDao.getAllLikelyNews(boolean)) }

    suspend fun getAllRecommendNew(type:String) = flow { emit(apiService.getAllNewsApi(type)) }

    suspend fun getAllTypeNews(type: String) = flow { emit(newsDao.getAllTypeNews(type)) }



    suspend fun addLikeNews(newsLikeEntity: NewsLikeEntity) = newsLikeDao.addNewsLike(newsLikeEntity)

    suspend fun getAllLikeNews() = flow { emit(newsLikeDao.getAllLikeNews()) }

    suspend fun deleteLikeNews(newsLikeEntity: NewsLikeEntity) = newsLikeDao.deleteLikeNews(newsLikeEntity)

}