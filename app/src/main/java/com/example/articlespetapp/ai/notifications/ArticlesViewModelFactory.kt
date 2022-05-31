package com.example.articlespetapp.ai.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articlespetapp.repository.database.ArticlesDatabase
import com.example.articlespetapp.repository.network.ArticlesRestRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesViewModelFactory @Inject constructor(val repository: ArticlesRestRepository, val database: ArticlesDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
            return ArticlesViewModel(repository, database) as T
        } else {
            throw IllegalStateException("ne tot klass")
        }
    }
}