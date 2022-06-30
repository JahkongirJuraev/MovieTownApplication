package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.response.main.home.latestMovies.SliderResponse
import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/upcoming")
    fun getSliderMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<SliderResponse?>>

    @GET("/3/movie/top_rated")
    fun getTopRatedMoviesRequest(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<TopRatedResponse?>>

    @GET("/3/movie/popular")
    fun getPopularMoviesRequest(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<PopularResponse?>>

    @GET("/3/movie/now_playing")
    fun getNowPlayingMoviesRequest(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<NowPlayingResponse?>>

}