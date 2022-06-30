package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.response.main.search.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Single<Response<SearchResponse?>>

}