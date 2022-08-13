package com.giftech.filmku.core.data.remote

import com.giftech.filmku.core.BuildConfig
import com.giftech.filmku.core.data.remote.network.ApiService
import com.giftech.filmku.core.utils.Constants
import com.giftech.filmku.core.utils.DummyData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api:ApiService
) {
    fun getTest() = "Constants.API_KEY"
    suspend fun getNowPlaying() = api.getNowPlaying()
}