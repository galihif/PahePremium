package com.giftech.filmku.core.data

import com.giftech.filmku.core.data.remote.RemoteDataSource
import com.giftech.filmku.core.data.remote.dto.toMovie
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.repository.FilmRepository
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val remote:RemoteDataSource
):FilmRepository {
    override fun getTest(): String {
        return remote.getTest()
    }

    override fun getNowPlaying(): Flow<Resource<List<Movie>>> = remote.getNowPlaying()
}