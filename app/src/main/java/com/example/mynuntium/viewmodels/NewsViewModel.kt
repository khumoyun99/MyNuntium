package com.example.mynuntium.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.databse.NewsLikeEntity
import com.example.mynuntium.repository.NewsRepository
import com.example.mynuntium.utils.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkHelper: NetworkHelper
) :ViewModel(){


    fun getAllNews(type:String):StateFlow<NewsResource>{
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)
        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                val flow =  newsRepository.getAllNews(type)
                flow.catch {
                        stateFlow.emit(NewsResource.Error(it.message?:""))
                    }.collect {
                        if(it.isSuccessful){
                            val body = it.body()?.articles
                            val list = ArrayList<NewsEntity>()
                            if (body != null) {
                                for (i in body.indices){
                                    list.add(NewsEntity(
                                        id=i,
                                        author = body[i].author?:"",
                                        content = body[i].content?:"",
                                        description = body[i].description?:"",
                                        publishedAt = body[i].publishedAt?:"",
                                        title = body[i].title?:"",
                                        urlToImage = body[i].urlToImage?:"",
                                        isLike = false,
                                        type = type
                                    ))
                                }
                            }
                            newsRepository.addAllNews(list)
                            stateFlow.emit(NewsResource.Success(list))
                        }else{
                            stateFlow.emit(NewsResource.Error("Server error"))
                        }
                    }
            }else{
                stateFlow.emit(NewsResource.Error("No internet connection"))
                newsRepository.getDbNews()
                    .collect {
                        if(it.isNotEmpty()){
                            stateFlow.emit(NewsResource.Success(it))
                        }else{
                            stateFlow.emit(NewsResource.Error("No internet connection"))
                        }
                    }
            }
        }
        return stateFlow
    }

    suspend fun updateItemNews(newsEntity: NewsEntity){
        newsRepository.updateItemNews(newsEntity)
    }

    fun getAllRecommendNews(type:String):StateFlow<NewsResource>{
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)
        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                val flow =  newsRepository.getAllRecommendNew(type)
                flow.catch {
                    stateFlow.emit(NewsResource.Error(it.message?:""))
                }.collect {
                    if(it.isSuccessful){
                        val body = it.body()?.articles
                        val list = ArrayList<NewsEntity>()
                        if (body != null) {
                            for (i in body.indices){
                                list.add(NewsEntity(
                                    id = i,
                                    author = body[i].author?:"",
                                    content = body[i].content?:"",
                                    description = body[i].description?:"",
                                    publishedAt = body[i].publishedAt?:"",
                                    title = body[i].title?:"",
                                    urlToImage = body[i].urlToImage?:"",
                                    isLike = false,
                                    type = type
                                ))
                            }
                        }
//                        newsRepository.addAllNews(list)
                        stateFlow.emit(NewsResource.Success(list))
                    }else{
                        stateFlow.emit(NewsResource.Error("Server error"))
                    }
                }
            }else{
                stateFlow.emit(NewsResource.Error("No internet connection"))
            }
        }
        return stateFlow
    }

    suspend fun addLikeNews(newsLikeEntity: NewsLikeEntity){
        newsRepository.addLikeNews(newsLikeEntity)
    }

    suspend fun getAllLikelyNews():List<NewsLikeEntity>{
        var newsLikeList = ArrayList<NewsLikeEntity>()
        newsRepository.getAllLikeNews()
            .collect{
                newsLikeList = ArrayList(it)
            }
        return newsLikeList
    }

    suspend fun deleteLikeNews(newsLikeEntity: NewsLikeEntity){
        newsRepository.deleteLikeNews(newsLikeEntity)
    }





}