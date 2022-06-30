package com.example.movieapp.core.model.request.markAsFavoriteRequest

data class MarkAsFavoriteRequest(
    val favorite: Boolean,
    val media_id: Int,
    val media_type: String
)