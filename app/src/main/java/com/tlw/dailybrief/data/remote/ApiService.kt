package com.tlw.dailybrief.data.remote

import com.tlw.dailybrief.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/1/latest")
    suspend fun getLatestNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("language") language: String,
    ): NewsResponse
}