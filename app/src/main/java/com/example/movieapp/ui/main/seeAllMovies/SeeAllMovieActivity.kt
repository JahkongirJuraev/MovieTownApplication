package com.example.movieapp.ui.main.seeAllMovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.core.adapter.SeeAllMoviesAdapter
import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import com.example.movieapp.databinding.ActivitySeeAllMovieBinding
import com.example.movieapp.ui.base.BaseActivity
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.google.android.material.snackbar.Snackbar

class SeeAllMovieActivity : BaseActivity(), SeeAllMoviesMVP.View {

    private lateinit var binding: ActivitySeeAllMovieBinding
    private lateinit var presenter: SeeAllMoviesMVP.Presenter

    private var adapter: SeeAllMoviesAdapter= SeeAllMoviesAdapter()

    private lateinit var MovieType: String

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    override fun getView(): View? {
        binding = ActivitySeeAllMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        var intent = intent
        MovieType = intent.getStringExtra(MOVIE_TYPE)!!

        binding.movieType.text=MovieType

        //Now Playing Movies
        presenter = SeeAllMoviesPresenter(this)
        when (MovieType) {
            "Now Playing Movies" -> {
                presenter.loadNowPlayingMovies()
            }
            "Top Rated Movies" -> {
                presenter.loadTopRatedMovies()
            }
            else -> {
                presenter.loadPopularMovies()
            }
        }
        prepareRecyclerView()
        onRecyclerViewClicked()
    }

    private fun onRecyclerViewClicked() {
        adapter.onItemClicked={
            var intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        var layoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }

    override fun setPopularMovies(popularMoviesResponse: PopularResponse) {
        adapter.setData(popularMoviesResponse.results)
    }

    override fun setNowPlayingMovies(nowPlayingResponse: NowPlayingResponse) {
        adapter.setData(nowPlayingResponse.results)
        Log.d("TAG",nowPlayingResponse.dates.toString())
    }

    override fun setTopRatedMovies(topRatedResponse: TopRatedResponse) {
        adapter.setData(topRatedResponse.results)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}