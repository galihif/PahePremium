package com.giftech.filmku.core.domain.usecase

import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.repository.FilmRepository
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmInteractor @Inject constructor(
    private val repository: FilmRepository
):FilmUseCase {
    override fun getTest(): String = repository.getTest()
    override fun getNowPlaying(): Flow<Resource<List<Movie>>> = repository.getNowPlaying()
    override fun getPopular(): Flow<Resource<List<Movie>>> = repository.getPopular()
    override fun getMovie(movieId:Int): Flow<Resource<Movie>> = repository.getMovie(movieId)
    override suspend fun addMovieToWatchList(movie: Movie) = repository.addMovieToWatchList(movie)
    override fun getWatchlist(): Flow<List<Movie>> =  repository.getWatchlist()
}