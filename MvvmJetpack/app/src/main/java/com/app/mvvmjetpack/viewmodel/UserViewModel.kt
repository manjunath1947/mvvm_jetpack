package com.app.mvvmjetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mvvmjetpack.repo.NetworkModule
import com.app.mvvmjetpack.repo.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState : StateFlow<UiState> = _uiState
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val users = NetworkModule.apiService.getUsers()
                _uiState.value = UiState.Success(users)
            }catch (e : Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "An Unknown Error Occured")
            }
        }
    }
}