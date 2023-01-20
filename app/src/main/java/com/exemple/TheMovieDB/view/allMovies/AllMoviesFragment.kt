package com.exemple.TheMovieDB.view.allMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.exemple.TheMovieDB.adapter.allMovies.AllMoviesAdapter
import com.exemple.TheMovieDB.databinding.AllMoviesFragmentBinding
import com.exemple.TheMovieDB.model.response.base.BaseType
import com.exemple.TheMovieDB.presenter.interfaces.AllMoviePresenter
import com.exemple.TheMovieDB.presenter.impl.PresenterAllMovie
import com.exemple.TheMovieDB.view.base.BaseFragment

class AllMoviesFragment:BaseFragment(), AllMoviePresenter.View {

    private lateinit var binding: AllMoviesFragmentBinding
    private var adapter = AllMoviesAdapter ()
    private var presenter: AllMoviePresenter.Presenter? = null

    override fun onFragmentReady() {
        presenter = PresenterAllMovie(this)

        val movieType = arguments?.getString("movieType")

        binding.title.text = movieType
        binding.movieList.layoutManager = GridLayoutManager(requireActivity(),2)
        binding.movieList.adapter = adapter

        adapter.onAllMovieItemClick = {
            val action = AllMoviesFragmentDirections.actionAllMoviesFragmentToDetailFragment()
            action.arguments.putInt("movie_id",it)
            findNavController().navigate(action)
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        movieType?.let { presenter?.loadData(it) }
    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = AllMoviesFragmentBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun dataState(isLoading: Boolean) {

    }

    override fun showData(data: List<BaseType>) {
        adapter.setAllMovieData(data)
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}