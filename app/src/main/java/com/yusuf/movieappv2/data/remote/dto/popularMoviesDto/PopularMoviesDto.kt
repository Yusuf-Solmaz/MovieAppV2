package com.yusuf.movieappv2.data.remote.dto.popularMoviesDto

import com.yusuf.movieappv2.domain.model.Movie

data class PopularMoviesDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)



