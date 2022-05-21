package com.nageshempire.androidnews.util.view

import android.view.Menu

interface BaseFragment {
    fun onLayoutInflated()
    fun setupView()
    fun setupObservables()
    fun onMenuInflated(menu: Menu)
}