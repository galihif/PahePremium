package com.giftech.filmku.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.filmku.core.data.local.entity.MovieEntity
import com.giftech.filmku.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedMovieDao {
    @Query("SELECT * FROM movie")
    fun getAllSavedMovie():Flow<List<MovieEntity>>

    @Query("SELECT  * FROM movie WHERE id = :movieId")
    fun getMovieById(movieId:Int):List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedMovie(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id = :movieId")
    suspend fun deleteSavedMovieById(movieId:Int)
}
