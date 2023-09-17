package com.yusuf.movieappv2.domain.repository

import com.yusuf.movieappv2.domain.model.Movie

interface MovieRepository {

    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovieDetail (id: Int) : Movie

}