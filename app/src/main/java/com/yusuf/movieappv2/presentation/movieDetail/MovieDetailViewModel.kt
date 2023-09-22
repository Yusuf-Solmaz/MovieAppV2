package com.yusuf.movieappv2.presentation.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.movieappv2.domain.useCase.GetMovieDetailUseCase
import com.yusuf.movieappv2.presentation.movies.MoviesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): ViewModel() {

    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state

    private var job : Job? = null


    fun setImdbId(movieId: Int) {
        getMovieDetail(movieId)
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val movie = getMovieDetailUseCase(movieId)

                _state.value = MovieDetailState(isLoading = false,movie=movie,"")

            }
        catch (e: Exception){

        }}
    }
}

