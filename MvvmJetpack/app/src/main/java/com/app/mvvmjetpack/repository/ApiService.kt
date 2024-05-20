package com.app.mvvmjetpack.repository

import com.app.mvvmjetpack.data.UsersData
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getUsers() : List<UsersData>
}