package com.giftech.filmku.core.data.local

import android.util.Log
import com.giftech.filmku.core.data.local.entity.MovieEntity
import com.giftech.filmku.core.data.local.room.SavedMovieDao
import com.giftech.filmku.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: SavedMovieDao
) {
    suspend fun insertMovie(movieEntity: MovieEntity) {
        dao.insertSavedMovie(movieEntity)
        Log.d("GALIH", "saved")
    }

    fun getAllSavedMovie():Flow<List<MovieEntity>> = dao.getAllSavedMovie()

    suspend fun checkIsMovieSaved(movieId:Int):Boolean = dao.isMovieSaved(movieId)
}