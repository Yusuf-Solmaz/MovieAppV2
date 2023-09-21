package com.yusuf.movieappv2.presentation.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.movieappv2.R
import com.yusuf.movieappv2.databinding.FragmentMoviesBinding
import com.yusuf.movieappv2.domain.model.Movie
import com.yusuf.movieappv2.presentation.movies.MoviesViewModel
import com.yusuf.movieappv2.presentation.movies.adapter.MoviesAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies),MoviesAdapter.Listener {

    private var fragmentBinding: FragmentMoviesBinding? = null

    private val viewModel by viewModel<MoviesViewModel>()
    private lateinit var movieList: MutableList<Movie>

    private lateinit var movieAdapter: MoviesAdapter
    //    private var movieAdapter = MovieRecyclerViewAdapter(arrayListOf(),this)
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

        val binding = FragmentMoviesBinding.bind(view)
        fragmentBinding = binding

        val layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.layoutManager = layoutManager

        movieList = mutableListOf()

        movieAdapter = MoviesAdapter(movieList,this@MoviesFragment)
        binding.recyclerView.adapter = movieAdapter

        viewModel.getMovies()

        val state = viewModel.state

        lifecycleScope.launch {
            state.collect(){
                val newMovieList = it.movies

                if (newMovieList.isNotEmpty()){

                    movieList.clear()
                    movieList.addAll(newMovieList)
                    movieAdapter.updateData(newMovieList)
                    println("Liste: $movieList")
                }
            }
        }
    }

    override fun onItemClick(movieModel: Movie) {
        TODO("Not yet implemented")
    }
}