package com.giftech.filmku.presentation.detail

import androidx.lifecycle.*
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import com.giftech.filmku.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _isWatchList = MutableLiveData<Boolean>(false)
    val isWatchList:LiveData<Boolean> = _isWatchList

    private val _toastText = MutableLiveData<String>("")
    val toastText:LiveData<String> = _toastText


    fun setTaskId(movieId: Int) {
        if (movieId == _movieId.value || movieId==0) {
            return
        }
        _movieId.value = movieId
        checkIfMovieInWatchList(movieId)
    }

    private fun checkIfMovieInWatchList(movieId: Int) {
        viewModelScope.launch {
            _isWatchList.value = useCase.isMovieInWatchList(movieId)
        }
    }

    fun saveMovie(movie: Movie){
        viewModelScope.launch {
            if(_isWatchList.value!!){
                _toastText.value = "Sudah ditambahkan lur"
            }else{
                useCase.addMovieToWatchList(movie)
                _toastText.value = "Movie ditambahkan"
                _isWatchList.value = true
            }
        }
    }


}
