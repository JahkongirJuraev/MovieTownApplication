package com.example.movieapp.ui.main.seeAllMovies

import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMoviesResponse

interface SeeAllMoviesMVP {

    interface View {
        fun setPopularMovies(popularMoviesResponse: PopularResponse)
        fun setNowPlayingMovies(nowPlayingResponse: NowPlayingResponse)
        fun setTopRatedMovies(topRatedResponse: TopRatedResponse)
        fun onError(message:String)
    }

    interface Presenter {
        fun loadPopularMovies()
        fun loadNowPlayingMovies()
        fun loadTopRatedMovies()
        fun cancelRequest()
    }

}