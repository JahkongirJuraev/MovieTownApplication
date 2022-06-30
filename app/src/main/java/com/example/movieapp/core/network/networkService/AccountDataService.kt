package com.example.movieapp.core.network.networkService

import com.example.movieapp.core.model.request.deleteSession.DeleteSessionRequest
import com.example.movieapp.core.model.request.markAsFavoriteRequest.MarkAsFavoriteRequest
import com.example.movieapp.core.model.response.main.account.accountDetails.AccountDetailsResponse
import com.example.movieapp.core.model.response.main.account.deleteSession.DeleteSessionRespond
import com.example.movieapp.core.model.response.main.movieData.markAsFavorite.MarkAsFavoriteResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface AccountDataService {

    @GET("/3/account")
    fun getAccountData(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Single<Response<AccountDetailsResponse?>>

    @HTTP(method = "DELETE", path = "/3/authentication/session", hasBody = true)
   //@DELETE("/3/authentication/session")
    fun deleteSession(
        @Query("api_key") apiKey: String,
        @Body deleteSessionRequest: DeleteSessionRequest
    ): Single<Response<DeleteSessionRespond?>>

}