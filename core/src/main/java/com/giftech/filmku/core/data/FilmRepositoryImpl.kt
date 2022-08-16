package com.giftech.filmku.core.data

import com.giftech.filmku.core.data.local.LocalDataSource
import com.giftech.filmku.core.data.remote.RemoteDataSource
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.repository.FilmRepository
import com.giftech.filmku.core.utils.Mapper.domainToEntity
import com.giftech.filmku.core.utils.Mapper.entitiesToDomain
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val local:LocalDataSource,
    private val remote:RemoteDataSource
):FilmRepository {
    override fun getTest(): String {
        return remote.getTest()
    }

    override fun getNowPlaying(): Flow<Resource<List<Movie>>> = remote.getNowPlaying()
    override fun getPopular(): Flow<Resource<List<Movie>>> = remote.getPopular()
    override fun getMovie(movieId: Int): Flow<Resource<Movie>> = remote.getMovieDetail(movieId)
    override suspend fun addMovieToWatchList(movie: Movie) {
        local.insertMovie(domainToEntity(movie))
    }

    override fun getWatchlist(): Flow<List<Movie>> = local.getAllSavedMovie().map { entitiesToDomain(it) }
}