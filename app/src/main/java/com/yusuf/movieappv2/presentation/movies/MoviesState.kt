package com.yusuf.movieappv2.presentation.movies

import com.yusuf.movieappv2.domain.model.Movie

data class MoviesState(

    var loading: Boolean = false,
    var refreshing: Boolean= false,
    var movies: List<Movie> = listOf(),
    var errorMassage: String? = null,
    var loadingFinished: Boolean = false
)