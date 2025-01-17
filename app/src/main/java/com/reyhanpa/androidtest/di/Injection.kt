package com.reyhanpa.androidtest.di

import android.content.Context
import com.reyhanpa.androidtest.data.local.room.UsernameDatabase
import com.reyhanpa.androidtest.data.remote.retrofit.ApiConfig
import com.reyhanpa.androidtest.repositories.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = UsernameDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(database, apiService)
    }
}