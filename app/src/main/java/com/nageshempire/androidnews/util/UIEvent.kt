package com.nageshempire.androidnews.util

import androidx.lifecycle.Observer

open class UIEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

class UIEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<UIEvent<T>> {
    override fun onChanged(event: UIEvent<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}