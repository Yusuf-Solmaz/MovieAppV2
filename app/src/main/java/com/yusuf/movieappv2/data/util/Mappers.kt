package com.yusuf.movieappv2.data.util

import com.yusuf.movieappv2.data.remote.dto.movieDto.MovieDto
import com.yusuf.movieappv2.data.remote.dto.popularMoviesDto.PopularMoviesDto
import com.yusuf.movieappv2.domain.model.Movie

internal fun MovieDto.toMovie(): Movie
{
    return Movie(
        id = id,
        description = overview,
        imageUrl = poster_path,
        title = title,
        releaseDate = release_date
    )
}


fun PopularMoviesDto.toMovieDetail (): List<Movie>{
    return results.map{
            result ->  Movie(result.id,result.title,result.overview,result.poster_path,result.release_date)
    }
}

private fun getImageUrl(imageUrl: String) = "https://image.tmdb.org/t/p/w500/$imageUrl"