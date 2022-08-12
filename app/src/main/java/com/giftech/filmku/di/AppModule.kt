package com.giftech.filmku.di

import com.giftech.filmku.core.domain.usecase.FilmInteractor
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideUseCase(filmInteractor: FilmInteractor):FilmUseCase
}