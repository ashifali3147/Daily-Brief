package com.tlw.dailybrief.data.worker

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tlw.dailybrief.R
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.data.local.NewsDao
import com.tlw.dailybrief.data.mapper.toDomain
import com.tlw.dailybrief.data.remote.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PeriodicWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        try {
            val response = apiService.getLatestNews(Constants.NEWS_API_KEY, "in", "en")
            val newsList = response.results.map {
                it.toDomain(workType = Constants.PERIODIC_WORK_REQUEST)
            }

            if (newsList.isEmpty()) {
                return Result.success() // Nothing to notify
            }

            newsDao.insertNews(newsList)

            // Check permission only for Android 13+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return Result.success()
                }
            }

            val randomNews = newsList.randomOrNull()

            val notification =
                NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(randomNews?.title)
                    .setContentText(randomNews?.description)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .build()

            NotificationManagerCompat.from(context)
                .notify(System.currentTimeMillis().toInt(), notification)
            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }
}