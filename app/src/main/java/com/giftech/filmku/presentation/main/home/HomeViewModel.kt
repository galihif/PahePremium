package com.giftech.filmku.presentation.main.home

import androidx.lifecycle.*
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {
    val nowPlaying = LiveDataReactiveStreams.fromPublisher(useCase.getNowPlaying())
    val popular = useCase.getPopular().asLiveData()
}
