package com.example.mynuntium.databse

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class NewsEntity (
    @PrimaryKey
    val id:Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String,
    var isLike:Boolean,
    var type:String=""
):Serializable