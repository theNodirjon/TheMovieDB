package com.exemple.TheMovieDB.adapter.home.sub

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exemple.TheMovieDB.databinding.SecondSubItemBinding
import com.exemple.TheMovieDB.model.response.home.nowPlaying.Result

class NowPlayingMovieViewHolder(val binding: SecondSubItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: Result){
        binding.title.text = data.title
        binding.description.text = data.overview

        val posterImageUrl = "https://image.tmdb.org/t/p/original"+data.posterPath
        val imageUrl = "https://image.tmdb.org/t/p/original"+data.backdropPath

        binding.subImage.load(posterImageUrl)
        binding.mainImage.load(imageUrl)
    }
}