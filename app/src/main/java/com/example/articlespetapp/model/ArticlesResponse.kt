package com.example.articlespetapp.model

data class ArticlesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
)