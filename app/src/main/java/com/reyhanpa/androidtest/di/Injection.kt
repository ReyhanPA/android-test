package com.reyhanpa.androidtest.di

import android.content.Context
import com.reyhanpa.androidtest.data.local.room.UsernameDatabase
import com.reyhanpa.androidtest.data.pref.UserPreference
import com.reyhanpa.androidtest.data.pref.dataStore
import com.reyhanpa.androidtest.data.remote.retrofit.ApiConfig
import com.reyhanpa.androidtest.repositories.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = UsernameDatabase.getDatabase(context)
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(database, pref, apiService)
    }
}