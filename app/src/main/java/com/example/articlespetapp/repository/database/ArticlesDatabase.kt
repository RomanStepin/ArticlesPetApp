package com.example.articlespetapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.articlespetapp.model.Article

@Database(entities = [Article::class], version = 5, exportSchema = false)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun getDao(): ArticlesDao
}