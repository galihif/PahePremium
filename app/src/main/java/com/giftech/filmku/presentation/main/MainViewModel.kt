package com.giftech.filmku.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {

}
