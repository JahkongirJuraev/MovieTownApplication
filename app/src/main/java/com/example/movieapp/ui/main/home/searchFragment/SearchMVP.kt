package com.example.movieapp.ui.main.home.searchFragment

import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.core.model.response.main.search.SearchResponse

interface SearchMVP {

    interface View {
        fun getSearchResult(movieData: List<MovieData>)
        fun onError(message: String)
    }

    interface Presenter {
        fun loadSearchResult(query:String)
        fun cancelRequest()
    }
}