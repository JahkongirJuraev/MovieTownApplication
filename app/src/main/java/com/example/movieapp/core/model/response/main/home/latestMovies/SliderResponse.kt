package com.example.movieapp.core.model.response.main.home.latestMovies

import com.example.movieapp.core.model.response.main.home.BaseData

data class SliderResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieData>,
    val total_pages: Int,
    val total_results: Int
):BaseData(){
    override fun getType():Int {
        return TYPE_SLIDER
    }

}