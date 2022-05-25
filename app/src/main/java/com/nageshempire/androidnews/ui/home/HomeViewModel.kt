package com.nageshempire.androidnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nageshempire.androidnews.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val breakingNews = liveData {
        val news = newsRepository.getBreakingNews()
        emit(news)
    }
}