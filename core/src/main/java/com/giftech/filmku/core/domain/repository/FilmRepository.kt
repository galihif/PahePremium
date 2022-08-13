package com.giftech.filmku.core.domain.repository

import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getTest():String
    fun getNowPlaying(): Flow<Resource<List<Movie>>>
}