package com.reyhanpa.androidtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyhanpa.androidtest.data.pref.UserModel
import com.reyhanpa.androidtest.repositories.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    companion object{
        private const val TAG = "MainViewModel"
    }
}