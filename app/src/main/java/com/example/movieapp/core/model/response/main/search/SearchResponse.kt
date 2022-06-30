package com.example.movieapp.core.model.response.main.search

import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData

data class SearchResponse(
    val page: Int,
    val results: List<MovieData>,
    val total_pages: Int,
    val total_results: Int
)