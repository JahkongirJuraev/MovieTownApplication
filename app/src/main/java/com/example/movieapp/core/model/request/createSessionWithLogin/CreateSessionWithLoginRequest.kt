package com.example.movieapp.core.model.request.createSessionWithLogin

data class CreateSessionWithLoginRequest(
    val password: String,
    val request_token: String,
    val username: String
)