package com.reyhanpa.androidtest.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.reyhanpa.androidtest.data.pref.UserModel
import com.reyhanpa.androidtest.data.remote.response.DataItem
import com.reyhanpa.androidtest.repositories.Repository
import com.reyhanpa.androidtest.utils.Event
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: Repository) : ViewModel() {
    val usernames: LiveData<PagingData<DataItem>> =
        repository.getUsernames().cachedIn(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}