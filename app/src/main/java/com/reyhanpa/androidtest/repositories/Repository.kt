package com.reyhanpa.androidtest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import com.reyhanpa.androidtest.data.local.mediator.UsernameRemoteMediator
import com.reyhanpa.androidtest.data.local.room.UsernameDatabase
import com.reyhanpa.androidtest.data.remote.response.DataItem
import com.reyhanpa.androidtest.data.remote.retrofit.ApiService
import com.reyhanpa.androidtest.utils.toDataItem

class Repository private constructor(
    private val usernameDatabase: UsernameDatabase,
    private val apiService: ApiService
) {

    fun getUsernames(): LiveData<PagingData<DataItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UsernameRemoteMediator(usernameDatabase, apiService),
            pagingSourceFactory = {
//                StoryPagingSource(apiService)
                usernameDatabase.usernameDao().getAllUsernames()
            }
        ).liveData.map { pagingData ->
            pagingData.map { dataEntity ->
                dataEntity.toDataItem()
            }
        }

    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(usernameDatabase: UsernameDatabase, apiService: ApiService) = Repository(usernameDatabase, apiService)
    }
}