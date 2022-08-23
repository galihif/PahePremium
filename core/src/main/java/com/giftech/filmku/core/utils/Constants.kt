package com.giftech.filmku.core.utils

import com.giftech.filmku.core.BuildConfig

object Constants {
    val BASE_URL = System.getenv("BASE_URL") ?: BuildConfig.BASE_URL
    val API_KEY = System.getenv("API_KEY") ?: BuildConfig.API_KEY
}