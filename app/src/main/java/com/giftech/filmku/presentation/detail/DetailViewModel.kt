package com.giftech.filmku.presentation.detail

import androidx.lifecycle.*
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import com.giftech.filmku.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {

    private val _movieId = MutableLiveData<Int>()

    private val _movie = _movieId.switchMap { id ->
        useCase.getMovie(id).asLiveData()
    }
    val movie: LiveData<Resource<Movie>> = _movie

    fun setTaskId(movieId: Int) {
        if (movieId == _movieId.value || movieId==0) {
            return
        }
        _movieId.value = movieId
    }


}
