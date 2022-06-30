package com.example.movieapp.ui.main.home.homeFragment

import com.example.movieapp.core.model.response.main.home.BaseData
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData

interface HomeMVP {

    interface View {
        fun isLoading()

        fun setMovieData(data:BaseData)

        /*fun setTopRated()

        fun setTrailers()

        fun setTheater()*/

        fun onError(message:String)
    }

    interface Presenter {

        fun cancelRequest()

        fun loadSliderData()

        fun loadTopRated()

        fun loadPopular()

        fun loadNowPlaying()
    }
}