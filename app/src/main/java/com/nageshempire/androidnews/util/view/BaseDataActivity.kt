package com.nageshempire.androidnews.util.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseDataActivity<B : ViewDataBinding>(private val layout: Int) :
    AppCompatActivity() {
    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layout)
        _binding?.lifecycleOwner = this
        onLayoutInflated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun onLayoutInflated()

}