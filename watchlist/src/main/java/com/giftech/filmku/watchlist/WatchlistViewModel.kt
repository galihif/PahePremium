package com.giftech.filmku.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class WatchlistViewModel (
    useCase: FilmUseCase
): ViewModel() {
    val watchlist = useCase.getWatchlist().asLiveData()
}
