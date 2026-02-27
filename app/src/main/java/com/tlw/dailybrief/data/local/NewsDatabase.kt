package com.tlw.dailybrief.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tlw.dailybrief.core.util.Constants
import com.tlw.dailybrief.domain.model.News
import com.tlw.dailybrief.core.db.Converters

@Database(entities = [News::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, Constants.NEWS_DB_NAME).build()
    }


    abstract fun getNewsDao(): NewsDao
}