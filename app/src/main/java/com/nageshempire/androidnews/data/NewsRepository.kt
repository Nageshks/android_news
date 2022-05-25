package com.nageshempire.androidnews.data

import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.api.NewsApi
import com.nageshempire.androidnews.ui.home.HomeNewsItem
import com.nageshempire.androidnews.util.DataFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getBreakingNews(): List<HomeNewsItem> = withContext(Dispatchers.IO) {
        val response = newsApi.getBreakingNews()
        val serverBreakingNewsArticles = response.articles
        serverBreakingNewsArticles.map { article ->
            HomeNewsItem(
                avatarUrl = "android.resource://com.nageshempire.androidnews/${R.drawable.avatar}",
                avatarTitle = article.source.name,
                avatarTime = DataFormatter.getFormattedTime(article.publishedAt),
                thumbnailUrl = article.urlToImage,
                newsTitle = article.title ?: ""
            )
        }
    }
}