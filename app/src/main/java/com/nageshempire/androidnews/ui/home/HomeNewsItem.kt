package com.nageshempire.androidnews.ui.home

import java.text.SimpleDateFormat

data class HomeNewsItem(
    val avatarUrl: String,
    val avatarTitle: String,
    val avatarTime: Long,
    val thumbnailUrl: String,
    val newTitle: String,
) {
    fun getFormattedTime(): String = SimpleDateFormat.getDateTimeInstance().format(avatarTime)
}
