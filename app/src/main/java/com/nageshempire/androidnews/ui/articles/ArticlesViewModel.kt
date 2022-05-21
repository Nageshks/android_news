package com.nageshempire.androidnews.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor() : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is articles Fragment"
    }
    val text: LiveData<String> = _text
}