package com.example.articlespetapp.di

import com.example.articlespetapp.ai.notifications.ArticlesFragment
import com.example.articlespetapp.ai.notifications.ArticlesViewModelFactory
import com.example.articlespetapp.repository.database.ArticlesDatabase
import com.example.articlespetapp.repository.network.ArticlesRestRepository
import dagger.Component
import javax.inject.Singleton


@Component(modules = [RetrofitModule::class, DatabaseModule::class])
@Singleton
interface NotificationsComponent {
    fun getNotificationsViewModelFactory(): ArticlesViewModelFactory
    fun repository(): ArticlesRestRepository
    fun injectNotificationsFragment(articlesFragment: ArticlesFragment)
    fun database(): ArticlesDatabase
}