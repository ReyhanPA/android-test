package com.reyhanpa.androidtest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reyhanpa.androidtest.di.Injection
import com.reyhanpa.androidtest.repositories.Repository
import com.reyhanpa.androidtest.ui.main.MainViewModel
import com.reyhanpa.androidtest.ui.users.UsersViewModel
import com.reyhanpa.androidtest.ui.welcome.WelcomeViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            UsersViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            WelcomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(context: Context) = ViewModelFactory(Injection.provideRepository(context))
    }
}