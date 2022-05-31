package com.example.articlespetapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.JsonAdapter


@Entity
@JsonAdapter(ArticleDeserializer::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var author: String = "",
   // val content: String,
    //val description: String,
    //val publishedAt: String,
    //val source: Source,
    val title: String,
   // val url: String,
    val urlToImage: String = "",
    var pageNumber: Int = 1
)