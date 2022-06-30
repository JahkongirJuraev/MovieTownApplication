package com.example.movieapp.core.model.response.main.similarMovies

data class SimilarMoviesResponse(
    val page: Int,
    val results: List<SimilarMovieData>,
    val total_pages: Int,
    val total_results: Int
)