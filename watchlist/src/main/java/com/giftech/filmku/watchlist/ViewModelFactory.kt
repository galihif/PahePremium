package com.giftech.filmku.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val useCase: FilmUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> {
                WatchlistViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}