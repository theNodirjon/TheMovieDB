package com.exemple.TheMovieDB.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exemple.TheMovieDB.adapter.search.SearchMovieAdapter
import com.exemple.TheMovieDB.databinding.SearchFragmentBinding
import com.exemple.TheMovieDB.model.response.search.Result
import com.exemple.TheMovieDB.presenter.impl.PresenterSearch
import com.exemple.TheMovieDB.presenter.interfaces.SearchPresenter
import com.exemple.TheMovieDB.view.base.BaseFragment

class SearchFragment:BaseFragment(), SearchPresenter.View {

    private var adapter = SearchMovieAdapter()
    private lateinit var binding: SearchFragmentBinding
    private var presenter: SearchPresenter.Presenter? = null

    override fun onFragmentReady() {
        Toast.makeText(requireActivity(), "search movies", Toast.LENGTH_SHORT).show()

        presenter = PresenterSearch(this)

        binding.searchList.adapter = adapter
        binding.searchList.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        var movieName = ""
        binding.movieNameEdit.addTextChangedListener {
            movieName = it.toString()
            presenter?.loadData(movieName)
        }
        adapter.onSearchItemClick = {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
            action.arguments.putInt("movie_id",it)
            findNavController().navigate(action)
        }
        /*binding.searchBtn.setOnClickListener {
            if(movieName != "") presenter?.loadData(movieName)
        }*/
    }


    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onFragmentCreated() {
    }

    override fun onFragmentClosed() {
        Toast.makeText(requireActivity(), "close", Toast.LENGTH_SHORT).show()
        presenter?.cancel()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun dataState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showSearchedData(data: List<Result>) {
        adapter.setSearchData(data)
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}