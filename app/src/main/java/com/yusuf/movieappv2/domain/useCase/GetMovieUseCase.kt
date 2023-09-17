package com.yusuf.movieappv2.domain.useCase

import com.yusuf.movieappv2.domain.model.Movie
import com.yusuf.movieappv2.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieUseCase : KoinComponent{
    private val repo: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie>{
        return repo.getMovies(page = page)
    }
}