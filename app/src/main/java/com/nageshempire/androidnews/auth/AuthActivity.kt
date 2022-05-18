package com.nageshempire.androidnews.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nageshempire.androidnews.databinding.ActivityAuthBinding
import com.nageshempire.androidnews.util.view.enableFullScreen

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()
    }

}