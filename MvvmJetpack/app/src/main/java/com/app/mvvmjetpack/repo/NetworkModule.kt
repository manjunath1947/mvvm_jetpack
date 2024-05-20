package com.app.mvvmjetpack.repo

import com.app.mvvmjetpack.repository.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {
/*    private const val BASE_URL = "https://example.com/api/"*/
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val apiService : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}