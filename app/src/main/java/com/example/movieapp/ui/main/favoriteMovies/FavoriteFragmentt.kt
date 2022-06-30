package com.example.movieapp.ui.main.favoriteMovies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.core.adapter.SearchAdapter
import com.example.movieapp.core.model.response.main.account.favoriteMovies.FavoriteMoviesRespond
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.ui.base.BaseFragment
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.google.android.material.snackbar.Snackbar

class FavoriteFragmentt : BaseFragment(), FavoriteMVP.View {

    private lateinit var presenter: FavoriteMVP.Presenter
    private lateinit var adapter: SearchAdapter

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val KEY_LINK="video_link"
    }

    /*FragmentTransaction tr = getFragmentManager().beginTransaction();
tr.replace(R.id.fragment_layout, instanceFragment);
tr.commit()*/

    private lateinit var binding: FragmentFavoriteBinding
    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        var tr:FragmentTransaction= requireFragmentManager().beginTransaction()
        presenter = FavoriteMoviesPresenter(this)
        presenter.loadFavoriteMovies()
        prepareRecyclerview()
    }

    private fun prepareRecyclerview() {
        adapter = SearchAdapter()
        binding.favoriteRecyclerview.adapter = adapter
        var layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.favoriteRecyclerview.layoutManager = layoutManager
        adapter.onItemClicked={
            var intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)

        }
    }

    override fun onViewDestroyed() {
        presenter.cancelRequest()
    }

    override fun getFavoriteMovie(favoriteMoviesResponse: FavoriteMoviesRespond) {
        if (favoriteMoviesResponse.results.isNotEmpty()) {
            if (favoriteMoviesResponse.results.isEmpty()) {
                binding.noMovieImage.visibility = View.VISIBLE
                binding.noMoviesText.visibility = View.VISIBLE
            } else {
                binding.favoriteRecyclerview.visibility = View.VISIBLE
                adapter.setData(favoriteMoviesResponse.results)
            }
        }

    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}