package com.giftech.filmku.core.domain.usecase

import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getTest():String
    fun getNowPlaying():Flow<Resource<List<Movie>>>
    fun getPopular():Flow<Resource<List<Movie>>>
    fun getMovie(movieId:Int):Flow<Resource<Movie>>
}