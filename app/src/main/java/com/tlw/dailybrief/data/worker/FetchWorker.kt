package com.tlw.dailybrief.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.data.local.NewsDao
import com.tlw.dailybrief.data.mapper.toDomain
import com.tlw.dailybrief.data.remote.ApiService

class FetchWorker(
    context: Context,
    params: WorkerParameters,
    private val apiService: ApiService,
    private val newsDao: NewsDao
): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        try {
            val response = apiService.getLatestNews(Constants.NEWS_API_KEY, "in", "en")
            val newsList = response.results.map {
                it.toDomain(workType = Constants.ONE_TYPE_WORK_REQUEST)
            }
            newsDao.insertNews(newsList)
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}