package com.giftech.filmku.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.domain.usecase.FilmUseCase
import com.giftech.filmku.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: FilmUseCase
): ViewModel() {
    fun getSearchResult(query:String):LiveData<Resource<List<Movie>>> =
        useCase.getSearchResult(query).asLiveData()
}
