package com.example.moviedbgraphql.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbgraphql.domain.model.DiscoverMoviesResponse
import com.example.moviedbgraphql.domain.model.Fail
import com.example.moviedbgraphql.domain.model.Result
import com.example.moviedbgraphql.domain.model.Success
import com.example.moviedbgraphql.domain.usecase.DiscoverMoviesUseCase
import com.example.moviedbgraphql.presentation.model.MovieUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: DiscoverMoviesUseCase
) : ViewModel() {

    private val _discoverMoviesResult = MutableLiveData<Result<List<MovieUiModel>>>()
    val discoverMoviesResult: LiveData<Result<List<MovieUiModel>>>
        get() = _discoverMoviesResult

    fun discoverMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _discoverMoviesResult.postValue(Success(useCase.execute()))
            } catch (e: Exception) {
                _discoverMoviesResult.postValue(Fail(e))
            }
        }
    }
}