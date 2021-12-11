package com.example.mynuntium.viewmodels

import com.example.mynuntium.databse.NewsEntity

sealed class NewsResource{

    object Loading:NewsResource()

    class Success(val list:List<NewsEntity>):NewsResource()

    class Error(val message:String):NewsResource()

}