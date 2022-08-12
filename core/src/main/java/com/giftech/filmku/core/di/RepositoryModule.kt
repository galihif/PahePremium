package com.giftech.filmku.core.di

import com.giftech.filmku.core.data.FilmRepositoryImpl
import com.giftech.filmku.core.domain.repository.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RemoteModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(filmRepositoryImpl: FilmRepositoryImpl):FilmRepository

}