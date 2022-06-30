package com.example.movieapp.core.model.response.main.home.nowPlayingMovie

import com.example.movieapp.core.model.response.main.home.BaseData
import com.example.movieapp.core.model.response.main.home.latestMovies.Dates
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.core.model.response.main.home.latestMovies.SliderResponse

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieData>,
    val total_pages: Int,
    val total_results: Int
):BaseData() {
    override fun getType(): Int {
        return TYPE_NOW_PLAYING
    }
}