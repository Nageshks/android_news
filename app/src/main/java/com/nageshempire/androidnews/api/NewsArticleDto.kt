package com.nageshempire.androidnews.api

data class NewsArticleDto(
    val title: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val source: Source
)

data class Source(
    val id: String?,
    val name: String
)