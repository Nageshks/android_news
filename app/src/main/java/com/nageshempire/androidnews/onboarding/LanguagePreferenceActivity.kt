package com.nageshempire.androidnews.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nageshempire.androidnews.databinding.ActivityLanguagePreferenceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagePreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLanguagePreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}