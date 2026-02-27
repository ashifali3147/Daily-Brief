package com.tlw.dailybrief.data.model

data class NewsResponse(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)