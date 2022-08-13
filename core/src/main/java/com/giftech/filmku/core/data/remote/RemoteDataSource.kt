package com.giftech.filmku.core.data.remote

import com.giftech.filmku.core.data.remote.network.ApiService
import com.giftech.filmku.core.utils.DummyData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api:ApiService
) {
    fun getTest() = "fuckckkkk"
    suspend fun getNowPlaying() = api.getNowPlaying()
}