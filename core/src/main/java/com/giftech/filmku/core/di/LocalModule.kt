package com.giftech.filmku.core.di

import android.content.Context
import androidx.room.Room
import com.giftech.filmku.core.data.local.LocalDataSource
import com.giftech.filmku.core.data.local.room.FilmDatabase
import com.giftech.filmku.core.data.local.room.SavedMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FilmDatabase =
        Room.databaseBuilder(
            context,
            FilmDatabase::class.java, "Film.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideSavedMovieDao(database: FilmDatabase): SavedMovieDao = database.savedMovieDao()

    @Provides
    @Singleton
    fun provideLocal(dao: SavedMovieDao):LocalDataSource = LocalDataSource(dao)
}