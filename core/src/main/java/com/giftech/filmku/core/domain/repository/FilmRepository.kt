package com.giftech.filmku.core.domain.repository

import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getNowPlaying(): Flow<Resource<List<Movie>>>
    fun getPopular(): Flow<Resource<List<Movie>>>
    fun getMovie(movieId: Int): Flow<Resource<Movie>>

    fun getSearchResult(query: String): Flow<Resource<List<Movie>>>

    suspend fun addMovieToWatchList(movie: Movie)
    suspend fun isMovieInWatchList(movieId: Int): Boolean
    suspend fun removeMovieFromWatchlist(movieId: Int)

    fun getWatchlist(): Flow<List<Movie>>
}