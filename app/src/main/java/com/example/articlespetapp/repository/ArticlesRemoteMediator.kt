package com.example.articlespetapp.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.articlespetapp.model.Article
import com.example.articlespetapp.repository.database.ArticlesDatabase
import com.example.articlespetapp.repository.network.ArticlesRestRepository
import retrofit2.HttpException
import java.io.IOException

const val PAGE_SIZE = 10

@ExperimentalPagingApi
class ArticlesRemoteMediator(private val database: ArticlesDatabase, val repository: ArticlesRestRepository): RemoteMediator<Int, Article>() {
    private val dao = database.getDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = false
                        )
                    lastItem.pageNumber + 1
                }
            }

            val response = repository.getPage(
                loadKey, PAGE_SIZE
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.deleteAll()
                }
                response.let { Log.d("LOGGGG", it.articles.toString())}
                response.let { dao.insertAll(it.articles) }
            }

            val isAll = response.articles.size.let { it < PAGE_SIZE }
            MediatorResult.Success (isAll)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}