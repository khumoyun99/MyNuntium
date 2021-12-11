package com.example.mynuntium.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NewsLikeEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String,
    var isLike:Boolean,
    var type:String=""
)