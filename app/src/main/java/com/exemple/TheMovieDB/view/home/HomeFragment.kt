package com.exemple.TheMovieDB.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exemple.TheMovieDB.adapter.home.main.MainAdapter
import com.exemple.TheMovieDB.databinding.HomeFragmentBinding
import com.exemple.TheMovieDB.model.HomeListData.MainListData
import com.exemple.TheMovieDB.model.response.base.BaseType
import com.exemple.TheMovieDB.model.response.home.popular.Result
import com.exemple.TheMovieDB.presenter.interfaces.MainPresenter
import com.exemple.TheMovieDB.presenter.impl.PresenterImp
import com.exemple.TheMovieDB.view.base.BaseFragment

class HomeFragment:BaseFragment(), MainPresenter.View {
    private lateinit var binding: HomeFragmentBinding

    private val mainAdapter = MainAdapter()

    private var presenter: MainPresenter.Presenter? = null

    override fun onFragmentReady() {

        presenter = PresenterImp(this)

        binding.mainList.adapter = mainAdapter
        binding.mainList.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        presenter?.loadData()

        binding.search.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        mainAdapter.allButtonClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToAllMoviesFragment()
            action.arguments.putString("movieType",it)
            findNavController().navigate(action)
        }

        mainAdapter.onMainListClick = object :MainAdapter.OnMainListClick{
            override fun onMainClick(movie_id: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                action.arguments.putInt("movie_id",movie_id)
                findNavController().navigate(action)
            }

        }
    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onFragmentCreated() {
        TODO("Not yet implemented")
    }

    override fun onFragmentClosed() {
        presenter?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun dataState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showPopularData(popularData: List<Result>) {
        mainAdapter.data.add(MainListData("Popular",popularData as List<BaseType>))
    }

    override fun showTopRatedData(topRatedData: List<com.exemple.TheMovieDB.model.response.home.topRated.Result>) {
        mainAdapter.data.add(MainListData("Top Rated",topRatedData as List<BaseType>))
    }

    override fun showNowPlayingData(nowPlayingData: List<com.exemple.TheMovieDB.model.response.home.nowPlaying.Result>) {
        mainAdapter.data.add(MainListData("Now Playing",nowPlayingData as List<BaseType>))
    }

    override fun showUpcoming(upcomingData: List<com.exemple.TheMovieDB.model.response.home.upcoming.Result>) {
        mainAdapter.data.add(MainListData("Upcoming",upcomingData as List<BaseType>))
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }


}