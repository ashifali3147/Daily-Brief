package com.tlw.dailybrief.data.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.data.local.NewsDao
import com.tlw.dailybrief.data.worker.FetchWorker
import com.tlw.dailybrief.data.worker.NotificationWorker
import com.tlw.dailybrief.data.worker.PeriodicWorker
import com.tlw.dailybrief.domain.model.News
import com.tlw.dailybrief.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class NewsRepoImpl(
    private val workManager: WorkManager,
    private val newsDao: NewsDao
) : NewsRepo {

    override fun getLatestNews() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()
        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .build()
        workManager.beginWith(workRequest)
            .then(notificationWorkRequest)
            .enqueue()
    }

    override fun getAllNews(): Flow<List<News>> = newsDao.getAllNews()

    override fun setPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        workManager.enqueueUniquePeriodicWork(
            Constants.PERIODIC_WORK_REQUEST_NAME,
            ExistingPeriodicWorkPolicy.UPDATE, workRequest
        )
    }
}