package com.yusuf.movieappv2.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.movieappv2.domain.useCase.GetMovieUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(val getMovieUseCase: GetMovieUseCase) : ViewModel() {


    private val _state = MutableStateFlow<MoviesState>(MoviesState())
    val state: StateFlow<MoviesState> = _state

    private var job : Job? = null

    private var currentPage = 1

    init {
        getMovies(forceReload = false)
    }

    fun getMovies(forceReload: Boolean = false){
        if (_state.value.loading){
            return
        }
        if(forceReload){
            currentPage=1
        }
        if (currentPage==1){
            _state.value= MoviesState(refreshing = false)
        }

        viewModelScope.launch {
            _state.value = MoviesState(loading = true)

            try {
                val resultMovies= getMovieUseCase(page = currentPage)

                val movies = if (currentPage == 1 ) resultMovies else _state.value.movies + resultMovies

                currentPage +=1
                _state.value = MoviesState(loading = false,refreshing = false, loadingFinished = resultMovies.isEmpty(), movies = movies)
            }
            catch (error: Throwable){
                _state.value = MoviesState(loading = false,refreshing = false, loadingFinished = true, errorMassage = "No Data! ${error.localizedMessage}")
            }
        }
    }
}