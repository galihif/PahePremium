package com.giftech.filmku.di

import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WatchlistModuleDependencies {
    fun useCase(): FilmUseCase
}