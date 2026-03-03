package com.tlw.dailybrief.domain.use_cases

import com.tlw.dailybrief.data.repository.NewsRepoImpl
import javax.inject.Inject

class SetupPeriodicWorkRequest @Inject constructor(private val newsRepo: NewsRepoImpl) {
    operator fun invoke() = newsRepo.setPeriodicWorkRequest()
}