package com.example.articlespetapp.repository.network

import com.example.articlespetapp.App
import com.example.articlespetapp.R
import com.example.articlespetapp.model.ArticlesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRestRepository @Inject constructor(private val articlesApi: ArticlesApi)  {
    suspend fun getPage(pageNumber: Int, pageSize: Int): ArticlesResponse {
        val apiKey = App.getInstance().resources.getString(R.string.news_api_key)
        return articlesApi.getArticlesPage(mapOf(Pair("apiKey", apiKey), Pair("page", pageNumber.toString()), Pair("pageSize", pageSize.toString()))).also {
            it.articles.map { article ->
                article.pageNumber = pageNumber
            }
        }
    }
}