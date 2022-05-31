package com.example.articlespetapp.repository.network

import com.example.articlespetapp.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ArticlesApi {
    @GET("/v2/everything?q=Spartak")
    suspend fun getArticlesPage(@QueryMap filters: Map<String, String>): ArticlesResponse
}