package com.tlw.dailybrief.domain.repository

import com.tlw.dailybrief.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getLatestNews()

    fun getAllNews(): Flow<List<News>>

    fun setPeriodicWorkRequest()
}