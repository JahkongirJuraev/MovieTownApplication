package com.example.movieapp.core.model.response.login.createRequestToken

data class CreateRequestTokenResponse(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)