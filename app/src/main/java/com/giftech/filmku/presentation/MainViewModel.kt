package com.giftech.filmku.presentation

import androidx.lifecycle.ViewModel
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {
    val message = useCase.getTest()
}
