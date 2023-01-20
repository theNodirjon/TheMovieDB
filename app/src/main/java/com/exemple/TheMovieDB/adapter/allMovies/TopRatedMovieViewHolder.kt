package com.exemple.TheMovieDB.adapter.allMovies

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exemple.TheMovieDB.databinding.AllMoviesItemBinding
import com.exemple.TheMovieDB.model.response.home.topRated.Result

class TopRatedMovieViewHolder(val binding: AllMoviesItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: Result){
        binding.title.text = data.title
        val imageUrl = "https://image.tmdb.org/t/p/original"+data.posterPath
        binding.image.load(imageUrl)
    }
}