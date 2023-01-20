package com.exemple.TheMovieDB.presenter.interfaces

import com.exemple.TheMovieDB.model.response.search.Result

interface SearchPresenter {
    interface Presenter{
        fun loadData(name:String)
        fun refreshData()
        fun cancel()
        fun destroy()
    }

    interface View{
        fun dataState(isLoading: Boolean)
        fun showSearchedData(data:List<Result>)
        fun showError(message: String)
    }
}