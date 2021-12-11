package com.example.mynuntium.networking

import com.example.mynuntium.models.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apiKey=b92a2b1ad61a459eaa86c5d57c4ae50b")
    suspend fun getAllNewsApi(@Query("q")keyword:String):Response<NewsApi>
}