package com.giftech.filmku.core.domain.usecase

import com.giftech.filmku.core.domain.repository.FilmRepository
import javax.inject.Inject

class FilmInteractor @Inject constructor(
    private val repository: FilmRepository
):FilmUseCase {
    override fun getTest(): String = repository.getTest()
}