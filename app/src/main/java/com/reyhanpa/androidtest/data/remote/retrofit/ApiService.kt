package com.reyhanpa.androidtest.data.remote.retrofit

import com.reyhanpa.androidtest.data.remote.response.UsernameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsername(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): UsernameResponse

}