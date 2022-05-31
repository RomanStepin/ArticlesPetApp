package com.example.articlespetapp.di

import androidx.room.Room
import com.example.articlespetapp.App
import com.example.articlespetapp.repository.database.ArticlesDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun getArticlesDatabase(): ArticlesDatabase {
        return Room.databaseBuilder(
            App.getInstance().baseContext,
            ArticlesDatabase::class.java,
            "base"
        ).fallbackToDestructiveMigration().build()
    }
}