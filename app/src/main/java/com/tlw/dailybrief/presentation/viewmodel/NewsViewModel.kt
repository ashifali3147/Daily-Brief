package com.tlw.dailybrief.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlw.dailybrief.domain.model.News
import com.tlw.dailybrief.domain.use_cases.GetNewsFromDbUseCase
import com.tlw.dailybrief.domain.use_cases.GetNewsUseCase
import com.tlw.dailybrief.domain.use_cases.SetupPeriodicWorkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    getNewsFromDbUseCase: GetNewsFromDbUseCase,
    setupPeriodicWorkRequest: SetupPeriodicWorkRequest
) : ViewModel() {

    val uiState = getNewsFromDbUseCase.invoke()
        .map { UiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState(emptyList()))


    init {
        setupPeriodicWorkRequest.invoke()

    }

    fun getNews() = getNewsUseCase.invoke()
}

data class UiState(val data: List<News>)