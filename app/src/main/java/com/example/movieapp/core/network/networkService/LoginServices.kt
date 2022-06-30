package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.request.createSessionRequest.CreateSessionRequest
import com.example.movieapp.core.model.request.createSessionWithLogin.CreateSessionWithLoginRequest
import com.example.movieapp.core.model.response.login.createRequestToken.CreateRequestTokenResponse
import com.example.movieapp.core.model.response.login.createSession.CreateSessionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail.AccountDetailResponse

interface LoginServices {

    //1
    @GET("/3/authentication/token/new")
    fun createRequestToken(@Query("api_key") apiKey: String): Single<Response<CreateRequestTokenResponse?>>

    //2
    @POST("/3/authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Query("api_key") apiKey: String,
        @Body createSessionWithLoginRequest: CreateSessionWithLoginRequest
    ): Single<Response<CreateRequestTokenResponse?>>

    //3
    @POST("/3/authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String,
        @Body createSessionRequest: CreateSessionRequest
    ): Single<Response<CreateSessionResponse?>>

    //4
    @GET("/3/account")
    fun getAccountDetails(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Single<Response<AccountDetailResponse?>>


}