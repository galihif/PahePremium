package com.giftech.filmku.presentation.main.search

import android.util.Log
import androidx.lifecycle.*
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import com.giftech.filmku.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {

    private val _keyword = MutableLiveData<String>()
    val keyword:LiveData<String> = _keyword

    val movieResults:LiveData<Resource<List<Movie>>> = _keyword.switchMap {
        useCase.getSearchResult(it).asLiveData()
    }

    fun setKeyword(query:String){
        _keyword.value = query
    }
}
