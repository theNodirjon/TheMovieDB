package com.exemple.TheMovieDB.presenter.interfaces

import com.exemple.TheMovieDB.model.response.base.BaseType

interface AllMoviePresenter {
    interface Presenter{
        fun loadData(movieType:String)
        fun cancel()
        fun destroy()
    }

    interface View{
        fun dataState(isLoading: Boolean)
        fun showData(data:List<BaseType>)
        fun showError(message: String)
    }
}