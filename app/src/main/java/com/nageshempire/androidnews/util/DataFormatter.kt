package com.nageshempire.androidnews.util

import android.annotation.SuppressLint
import android.text.format.DateUtils
import org.threeten.bp.Instant
import java.text.DateFormat


@SuppressLint("SimpleDateFormat")
object DataFormatter {

    fun getFormattedTime(serverDate: String): String {
        kotlin.runCatching {
            Instant.parse(serverDate)
        }.onSuccess {
            val millis = it.toEpochMilli()
            val day = getRelativeDay(millis)
            val hours = DateFormat.getTimeInstance(DateFormat.SHORT).format(millis)
            return "$day $hours"
        }
        return serverDate
    }

    private fun getRelativeDay(time: Long): String {
        return if (DateUtils.isToday(time)) {
            ""
        } else DateUtils.getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.DAY_IN_MILLIS
        ).toString()
    }
}