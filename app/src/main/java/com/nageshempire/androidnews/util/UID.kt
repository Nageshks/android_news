package com.nageshempire.androidnews.util

import java.util.concurrent.atomic.AtomicInteger

object UID {
    private val ids = AtomicInteger()
    fun getUID() = ids.getAndIncrement()
}