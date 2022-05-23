package com.nageshempire.androidnews.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage", "placeHolder", requireAll = false)
    fun loadImage(view: ImageView, address: String, placeholder: Drawable?) {
        var glide = Glide.with(view.context).load(address)
        if (placeholder != null) glide = glide.placeholder(placeholder)
        glide.into(view)
    }
}