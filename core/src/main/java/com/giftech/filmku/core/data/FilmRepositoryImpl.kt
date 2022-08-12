package com.giftech.filmku.core.data

import com.giftech.filmku.core.data.remote.RemoteDataSource
import com.giftech.filmku.core.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val remote:RemoteDataSource
):FilmRepository {
    override fun getTest(): String {
        return remote.getTest()
    }
}