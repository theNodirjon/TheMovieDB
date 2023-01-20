package com.exemple.TheMovieDB.presenter.impl

import com.exemple.TheMovieDB.model.network.MovieClient
import com.exemple.TheMovieDB.model.response.search.SearchMovieResponse
import com.exemple.TheMovieDB.presenter.interfaces.SearchPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterSearch(private val view: SearchPresenter.View): SearchPresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var currentTime = System.currentTimeMillis()

    override fun loadData(name: String) {
        val searchTime = System.currentTimeMillis()

        if((currentTime+2000)>searchTime){
            return
        }
        currentTime = System.currentTimeMillis()

        val call = MovieClient.getApiMethods().searchMovie(name)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchMovieResponse>(){
                    override fun onSuccess(t: SearchMovieResponse) {
                        view.showSearchedData(t.results)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }

    override fun refreshData() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}