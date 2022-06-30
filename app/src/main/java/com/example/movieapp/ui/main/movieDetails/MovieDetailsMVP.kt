package com.example.movieapp.ui.main.movieDetails

import com.example.movieapp.core.model.response.main.account.favoriteMovies.FavoriteMoviesRespond
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.core.model.response.main.movieData.movieDetails.MovieDetailsResponse
import com.example.movieapp.core.model.response.main.movieTrailer.MovieTrailerResponse
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMovieData
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMoviesResponse

interface MovieDetailsMVP {

    interface View {

        fun setMovieDetails(movieDetails: MovieDetailsResponse)

        fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse)

        fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse)

        fun getFavoriteMoviesList(favoriteMoviesListRespond: List<MovieData>)

        fun getFavoriteResult(success:Boolean)

        fun onError(message: String)

    }

    interface Presenter {

        fun cancelRequest()

        fun loadMovieDetails(movieId: Int)

        fun loadSimilarMovies(movieId: Int)

        fun loadMovieTrailer(movieId: Int)

        fun markAsFavorite(movieId: Int)

        fun markAsNotFavorite(movieId: Int)

        fun loadFavoriteMovies()
    }

}