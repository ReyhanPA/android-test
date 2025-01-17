package com.reyhanpa.androidtest.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reyhanpa.androidtest.data.pref.UserModel
import com.reyhanpa.androidtest.repositories.Repository

class WelcomeViewModel(private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    companion object{
        private const val TAG = "WelcomeViewModel"
    }
}