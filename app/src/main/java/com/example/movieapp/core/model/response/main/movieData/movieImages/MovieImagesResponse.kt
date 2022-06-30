package com.example.movieapp.core.model.response.main.movieData.movieImages

data class MovieImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)