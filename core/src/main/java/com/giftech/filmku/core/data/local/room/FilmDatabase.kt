package com.giftech.filmku.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giftech.filmku.core.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun savedMovieDao():SavedMovieDao

}