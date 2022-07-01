package com.example.movieapp.ui.main.home.homeFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.core.adapter.HomeAdapter
import com.example.movieapp.core.model.response.main.home.BaseData
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.base.BaseFragment
import com.example.movieapp.ui.main.seeAllMovies.SeeAllMovieActivity
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.google.android.material.snackbar.Snackbar

class HomeFragment : BaseFragment(), HomeMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE="Movie_Type"
        lateinit var handler: android.os.Handler
    }

    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeMVP: HomeMVP.Presenter

    private var adapter = HomeAdapter()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        homeMVP = HomePresenter(this)
        loadData()
        setViewState()
        onRecyclerViewClicked()

        onSeeAllClicked()

        showNextPageOfSlide()

        //onSliderClicked()
    }

    private fun onSeeAllClicked() {
        adapter.onSeeAllNowPlayingClicked = {
            var intent = Intent(requireActivity(), SeeAllMovieActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }

        adapter.onSeeAllPopularClicked = {
            var intent = Intent(requireActivity(), SeeAllMovieActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }

        adapter.onSeeAllTopRatedClicked = {
            var intent = Intent(requireActivity(), SeeAllMovieActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }
    }

    private fun onRecyclerViewClicked() {
        adapter.onSliderClicked = {
            val intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            //AppCache.appCache!!.setMovieId(it.id)
            startActivity(intent)
        }

        adapter.onNowPlayingClicked = {
            val intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        adapter.onTopRatedClicked = {
            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        adapter.onPopularClciked = {
            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun showNextPageOfSlide() {

    }

    /*fun onSliderClicked() {
        sliderAdapter.onItemClicked={movieData ->
            val intent = Intent(requireActivity(), DeleteActivity::class.java)
            intent.putExtra(MOVIE_DATA, movieData.id)
            startActivity(intent)
        }
    }*/

    fun setViewState() {
        binding.homeList.adapter = adapter
        binding.homeList.layoutManager = LinearLayoutManager(requireContext())
    }

    fun loadData() {
        homeMVP.loadSliderData()
        homeMVP.loadTopRated()
        homeMVP.loadPopular()
        homeMVP.loadNowPlaying()
    }

    override fun onViewDestroyed() {
        homeMVP.cancelRequest()
        adapter.clearData()
    }

    override fun isLoading() {

    }

    override fun setMovieData(data: BaseData) {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility=View.GONE
        adapter.addData(data)
    }


    override fun onError(message: String) {
        message.let {
            Snackbar.make(
                binding.homeList, it, Snackbar.LENGTH_SHORT
            ).show()
        }
    }



}