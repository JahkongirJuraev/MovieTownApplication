package com.example.movieapp.ui.main.home.homeFragment

import com.example.movieapp.core.model.response.main.home.BaseData

interface HomeMVP {

    interface View {
        fun isLoading()

        fun setMovieData(data:BaseData)


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