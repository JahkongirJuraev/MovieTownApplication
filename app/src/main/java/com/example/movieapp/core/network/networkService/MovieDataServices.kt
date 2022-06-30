package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.request.markAsFavoriteRequest.MarkAsFavoriteRequest
import com.example.movieapp.core.model.response.main.movieData.markAsFavorite.MarkAsFavoriteResponse
import com.example.movieapp.core.model.response.main.movieData.movieDetails.MovieDetailsResponse
import com.example.movieapp.core.model.response.main.movieData.movieImages.MovieImagesResponse
import com.example.movieapp.core.model.response.main.movieTrailer.MovieTrailerResponse
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface MovieDataServices {

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MovieDetailsResponse?>>

    ///movie/{movie_id}/similar
    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page:Int
    ):Single<Response<SimilarMoviesResponse?>>

    ///movie/{movie_id}/images
    @GET("/3/movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MovieImagesResponse?>>

    @GET("/3/movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):Single<Response<MovieTrailerResponse?>>

    @HTTP(method = "POST", path = "/3/account/{account_id}/favorite", hasBody = true)
    //@GET("/3/account/{account_id}/favorite")
    fun markAsFavorite(
        @Path("account_id") accountId:Int,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Body markAsFavoriteRequest: MarkAsFavoriteRequest
    ):Single<Response<MarkAsFavoriteResponse?>>

}