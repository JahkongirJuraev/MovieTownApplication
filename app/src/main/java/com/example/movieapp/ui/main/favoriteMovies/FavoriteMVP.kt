package com.example.movieapp.ui.main.favoriteMovies

import com.example.movieapp.core.model.response.main.account.favoriteMovies.FavoriteMoviesRespond

interface FavoriteMVP {

    interface View {
        fun getFavoriteMovie(favoriteMoviesResponse: FavoriteMoviesRespond)
        fun onError(message:String)
    }

    interface Presenter {
        fun loadFavoriteMovies()
        fun cancelRequest()
    }
}