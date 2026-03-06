package com.tlw.dailybrief.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.data.local.NewsDao
import com.tlw.dailybrief.data.mapper.toDomain
import com.tlw.dailybrief.data.remote.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        try {
            val response = apiService.getLatestNews(Constants.NEWS_API_KEY, "in", "en")
            val newsList = response.results.map {
                it.toDomain(workType = Constants.ONE_TIME_WORK_REQUEST)
            }
            newsDao.insertNews(newsList)
            val randomNews = newsList.randomOrNull()
            val data = Data.Builder()
                .putString(Constants.NEWS_JSON_KEY, Gson().toJson(randomNews))
                .build()
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}