package com.tlw.dailybrief.data.repository

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.tlw.dailybrief.data.worker.FetchWorker
import com.tlw.dailybrief.domain.model.News
import com.tlw.dailybrief.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(private val workManager: WorkManager) : NewsRepo {

    override fun getLatestNews() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()
        workManager.enqueue(workRequest)
    }

    override fun getAllNews(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override fun setPeriodicWorkRequest() {
        TODO("Not yet implemented")
    }
}