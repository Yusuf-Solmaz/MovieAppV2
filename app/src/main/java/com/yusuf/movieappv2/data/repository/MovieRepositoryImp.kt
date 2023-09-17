package com.yusuf.movieappv2.data.repository

import com.yusuf.movieappv2.data.remote.MovieAPI
import com.yusuf.movieappv2.data.util.toMovie
import com.yusuf.movieappv2.data.util.toMovieDetail
import com.yusuf.movieappv2.domain.model.Movie
import com.yusuf.movieappv2.domain.repository.MovieRepository

class MovieRepositoryImp(
    private val api: MovieAPI
): MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
       return api.getPopularMovies(page = page).toMovieDetail()
    }

    override suspend fun getMovieDetail(id: Int): Movie {
        return api.getOneMovie(movieId = id).toMovie()
    }
}