package com.giftech.filmku.core.domain.usecase

import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.repository.FilmRepository
import com.giftech.filmku.core.utils.Resource
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmInteractor @Inject constructor(
    private val repository: FilmRepository
):FilmUseCase {
    override fun getTest(): String = repository.getTest()

    override fun getNowPlaying(): Flowable<Resource<List<Movie>>> = repository.getNowPlaying()
    override fun getPopular(): Flow<Resource<List<Movie>>> = repository.getPopular()
    override fun getMovie(movieId:Int): Flow<Resource<Movie>> = repository.getMovie(movieId)

    override suspend fun addMovieToWatchList(movie: Movie) = repository.addMovieToWatchList(movie)
    override suspend fun isMovieInWatchList(movieId: Int) = repository.isMovieInWatchList(movieId)
    override suspend fun removeMovieFromWatchlist(movieId: Int) = repository.removeMovieFromWatchlist(movieId)

    override fun getWatchlist(): Flow<List<Movie>> =  repository.getWatchlist()
}