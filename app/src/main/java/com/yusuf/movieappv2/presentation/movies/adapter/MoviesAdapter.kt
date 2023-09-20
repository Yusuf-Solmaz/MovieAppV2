package com.yusuf.movieappv2.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.animation.AnimatableView.Listener
import com.yusuf.movieappv2.databinding.MovieRecyclerViewRowBinding
import com.yusuf.movieappv2.domain.model.Movie
import com.yusuf.movieappv2.presentation.movies.view.MoviesFragmentDirections

class MoviesAdapter(private val movieList: MutableList<Movie>, private val listener: Listener):
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    interface Listener {
        fun onItemClick(movieModel: Movie)
    }

    class MoviesHolder(val binding: MovieRecyclerViewRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val itemBinding = MovieRecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieList[position].id)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.movieTitle.text = movieList[position].title
        holder.binding.movieReleaseDate.text = movieList[position].releaseDate

    }

    fun updateData(newMovieList: List<Movie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}