package com.tlw.dailybrief.data.repository

import com.tlw.dailybrief.domain.model.News
import com.tlw.dailybrief.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl: NewsRepo {
    override fun getNewsDetail() {
        TODO("Not yet implemented")
    }

    override fun getAllNews(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override fun setPeriodicWorkRequest() {
        TODO("Not yet implemented")
    }
}