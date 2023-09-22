package com.yusuf.movieappv2.presentation.movieDetail

import com.yusuf.movieappv2.domain.model.Movie

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String = ""
)