package com.yusuf.movieappv2.data.remote

import com.yusuf.movieappv2.data.remote.dto.MovieDto
import com.yusuf.movieappv2.data.remote.dto.PopularMoviesDto
import com.yusuf.movieappv2.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    //API Key: 77396af91815c9da24186f792a95e391

    //Example Request:
    //https://api.themoviedb.org/3/movie/550?api_key=77396af91815c9da24186f792a95e391

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")  apiKey: String = API_KEY,
    ) : PopularMoviesDto

    @GET("3/movie/{movieId}")
    suspend fun getOneMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieDto
}