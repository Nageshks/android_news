package com.nageshempire.androidnews.ui.privacypolicy

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nageshempire.androidnews.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModel @Inject constructor(
    app: Application
) : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    init {
        _data.value = app.getString(R.string.lorem_ipsum)
    }
}