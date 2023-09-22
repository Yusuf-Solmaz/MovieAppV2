package com.yusuf.movieappv2.di

import com.yusuf.movieappv2.data.remote.MovieAPI
import com.yusuf.movieappv2.data.repository.MovieRepositoryImp
import com.yusuf.movieappv2.domain.repository.MovieRepository
import com.yusuf.movieappv2.domain.useCase.GetMovieDetailUseCase
import com.yusuf.movieappv2.domain.useCase.GetMovieUseCase
import com.yusuf.movieappv2.presentation.movieDetail.MovieDetailViewModel
import com.yusuf.movieappv2.presentation.movies.MoviesViewModel
import com.yusuf.movieappv2.util.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    single<MovieRepository> {
        MovieRepositoryImp(get())
    }

    viewModel {
        MoviesViewModel(get())
    }

    viewModel{
        MovieDetailViewModel(get())
    }

    factory { GetMovieDetailUseCase() }
    factory { GetMovieUseCase() }


}