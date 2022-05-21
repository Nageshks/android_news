package com.nageshempire.androidnews.util

import android.net.Uri

fun Int.toResourceUri(): Uri {
    return Uri.parse("android.resource://com.nageshempire.androidnews/$this")
}