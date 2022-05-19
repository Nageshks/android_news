package com.nageshempire.androidnews.onboarding

import com.nageshempire.androidnews.util.UID

data class Language(
    val name: String,
    val checked: Boolean = false,
    val id: Int = UID.getUID()
)
