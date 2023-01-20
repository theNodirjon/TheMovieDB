package com.exemple.TheMovieDB.presenter.interfaces

import com.exemple.TheMovieDB.model.response.details.actors.Cast
import com.exemple.TheMovieDB.model.response.details.detail.DetailResponse

interface DetailPresenter {
    interface Presenter{
        fun loadData(movie_id:Int)
        fun refreshData()
        fun cancel()
        fun destroy()
    }

    interface View{
        fun dataState(isLoading: Boolean)
        fun showSimilarMovies(similar:List<com.exemple.TheMovieDB.model.response.details.similar.Result>)
        fun showActors(actor:List<Cast>)
        fun showDetails(data: DetailResponse)
        fun showTrailers(trailers:List<com.exemple.TheMovieDB.model.response.details.trailer.Result>)
        fun showError(message: String)
    }
}