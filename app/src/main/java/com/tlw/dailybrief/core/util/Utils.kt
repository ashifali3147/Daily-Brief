package com.tlw.dailybrief.core.util

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun formatTimestampToDMY(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }

    fun formatTimestampToTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}