package com.nageshempire.androidnews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.nageshempire.androidnews.auth.AuthActivity
import com.nageshempire.androidnews.databinding.ActivityLauncherBinding
import com.nageshempire.androidnews.util.view.enableFullScreen
import java.lang.Compiler.enable

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LauncherActivity : AppCompatActivity() {

    private val handler = Handler(Looper.myLooper()!!)
    private val launchRunnable = Runnable {
        val target = if(false)MainActivity::class.java else AuthActivity::class.java
        startActivity(Intent(this,target))
        finish()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delayedLaunch()
    }

    private fun delayedLaunch(delayMillis: Int = 1_000) {
        handler.removeCallbacks(launchRunnable)
        handler.postDelayed(launchRunnable, delayMillis.toLong())
    }
}