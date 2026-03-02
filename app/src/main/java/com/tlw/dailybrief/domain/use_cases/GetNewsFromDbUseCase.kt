package com.tlw.dailybrief.domain.use_cases

import com.tlw.dailybrief.domain.repository.NewsRepo
import javax.inject.Inject

class GetNewsFromDbUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    operator fun invoke() = newsRepo.getAllNews()
}