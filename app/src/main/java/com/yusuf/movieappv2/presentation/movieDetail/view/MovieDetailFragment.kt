package com.yusuf.movieappv2.presentation.movieDetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yusuf.movieappv2.R
import com.yusuf.movieappv2.data.util.getImageUrl
import com.yusuf.movieappv2.databinding.FragmentMovieDetailBinding
import com.yusuf.movieappv2.presentation.movieDetail.MovieDetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private var fragmentBinding: FragmentMovieDetailBinding? = null
    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        fragmentBinding = binding

        arguments?.let {
            val movieId = MovieDetailFragmentArgs.fromBundle(it).movieId
            viewModel.setImdbId(movieId= movieId)
        }

        val state = viewModel.state

        lifecycleScope.launch {
            state.collect{
                val movie = it.movie
                if (movie!=null){
                    binding.movieName.text = movie.title
                    binding.movieDetails.text = movie.description
                    binding.movieReleaseDate.text = movie.releaseDate

                    Glide.with(requireContext())
                        .load(getImageUrl(movie.imageUrl))
                        .placeholder(R.drawable.img)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.recyclerViewImage)
                }

            }
        }
    }
}