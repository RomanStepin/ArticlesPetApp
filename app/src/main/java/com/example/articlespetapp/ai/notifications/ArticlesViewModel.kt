package com.example.articlespetapp.ai.notifications

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.articlespetapp.repository.database.ArticlesDatabase
import com.example.articlespetapp.repository.ArticlesRemoteMediator
import com.example.articlespetapp.repository.network.ArticlesRestRepository

class ArticlesViewModel (private val articlesRestRepository: ArticlesRestRepository, private val articlesDatabase: ArticlesDatabase) : ViewModel() {
    @ExperimentalPagingApi
    val flow = Pager(config = PagingConfig(10), remoteMediator = ArticlesRemoteMediator(articlesDatabase, articlesRestRepository)) {
        articlesDatabase.getDao().pagingSource()
    }.flow
}