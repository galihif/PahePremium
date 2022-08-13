package com.giftech.filmku.core.data.remote

import com.giftech.filmku.core.utils.DummyData

class RemoteDataSource {
    fun getTest() = "fuckckkkk"
    fun getNowPlaying() = DummyData.getListMovie()
}