package com.tlw.dailybrief.data.di

import android.content.Context
import androidx.work.WorkManager
import com.tlw.dailybrief.BuildConfig
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.data.local.NewsDao
import com.tlw.dailybrief.data.local.NewsDatabase
import com.tlw.dailybrief.data.remote.ApiService
import com.tlw.dailybrief.data.repository.NewsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsDataBase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.getInstance(context)
    }

    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.getNewsDao()
    }

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun getNewsRepo(workManager: WorkManager, newsDao: NewsDao): NewsRepoImpl {
        return NewsRepoImpl(workManager, newsDao)
    }
}