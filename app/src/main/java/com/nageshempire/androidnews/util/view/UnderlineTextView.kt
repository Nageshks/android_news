package com.nageshempire.androidnews.util.view

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView

class UnderlineTextView constructor(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {

    init {
        init()
    }

    private fun init() {
        paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}