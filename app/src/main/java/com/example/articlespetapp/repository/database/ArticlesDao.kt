package com.example.articlespetapp.repository.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.articlespetapp.model.Article

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(article: List<Article>)

    @Query("SELECT * FROM Article")
    fun pagingSource(): PagingSource<Int, Article>

    @Query("DELETE FROM Article")
    suspend fun deleteAll()
}