package com.nageshempire.androidnews.ui.mycity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyCityViewModel @Inject constructor() : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is my city Fragment"
    }
    val text: LiveData<String> = _text
}