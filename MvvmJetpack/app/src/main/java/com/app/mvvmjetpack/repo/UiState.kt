package com.app.mvvmjetpack.repo

import com.app.mvvmjetpack.data.UsersData

sealed class UiState {
    object Loading : UiState()
    data class Success(val user : List<UsersData>) : UiState()
    data class Error(val message : String) : UiState()
}
