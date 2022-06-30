package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.response.main.account.addwatchList.FavoriteMoviesRespond
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteMoviesService {
    @GET("/3/account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ):Single<Response<FavoriteMoviesRespond?>>
}