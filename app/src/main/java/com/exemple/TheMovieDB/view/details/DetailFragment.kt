package com.exemple.TheMovieDB.view.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.exemple.TheMovieDB.adapter.detail.main.DetailAdapter
import com.exemple.TheMovieDB.databinding.DetailFragmentBinding
import com.exemple.TheMovieDB.model.DetaiListData.DetailListData
import com.exemple.TheMovieDB.model.response.details.actors.Cast
import com.exemple.TheMovieDB.model.response.details.detail.DetailResponse
import com.exemple.TheMovieDB.model.response.details.similar.Result
import com.exemple.TheMovieDB.presenter.impl.PresenterDetail
import com.exemple.TheMovieDB.presenter.interfaces.DetailPresenter
import com.exemple.TheMovieDB.view.base.BaseFragment
import kotlin.math.roundToInt

class DetailFragment :BaseFragment(),DetailPresenter.View{
    
    private lateinit var binding: DetailFragmentBinding
    private var presenter: DetailPresenter.Presenter? = null

    private var mainListData = ArrayList<DetailListData>()

    private var detailAdapter = DetailAdapter()

    override fun onFragmentReady() {
        presenter = PresenterDetail(this)

        binding.mainList.adapter = detailAdapter
        binding.mainList.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        val movieId = arguments?.getInt("movie_id")
        if (movieId != null) {
            presenter?.loadData(movieId)
        }

        detailAdapter.data = mainListData

    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DetailFragmentBinding.inflate(layoutInflater)
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

    override fun showSimilarMovies(similar: List<Result>) {
        detailAdapter.data.add(DetailListData("Similar",similar))
        mainListData.add(DetailListData("Similar",similar))
    }

    override fun showActors(actor:List<Cast>) {
        detailAdapter.data.add(DetailListData("Actors",actor))
        mainListData.add(DetailListData("Actors",actor))
    }

    override fun showDetails(data: DetailResponse) {
        val imageUrl = "https://image.tmdb.org/t/p/original"+data.backdropPath
        val posterImg = "https://image.tmdb.org/t/p/original"+data.posterPath
        binding.image.load(posterImg)
        binding.backImage.load(imageUrl)
        binding.overview1.text = data.tagline
        binding.overview.text = data.overview
        binding.title.text = data.title
        binding.originalTitle.text = data.originalTitle
        binding.status.text = data.status
        binding.revenue.text = "$".plus(data.revenue.toString())
        binding.score.text = data.voteAverage.roundToInt().toString()
        binding.originalLanguage.text = data.originalLanguage.uppercase()

        binding.genre.text = data.releaseDate.substring(0,4).plus(",").plus(data.productionCountries[0].iso31661)
            .plus(",").plus(data.genres[0].name)

    }

    override fun showTrailers(trailers: List<com.exemple.TheMovieDB.model.response.details.trailer.Result>) {
        detailAdapter.data.add(DetailListData("Trailers",trailers))
        mainListData.add(DetailListData("Trailers",trailers))
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}