package com.example.movieapp.ui.login

import com.example.movieapp.core.model.response.login.createRequestToken.CreateRequestTokenResponse
import uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail.AccountDetailResponse

interface LoginMVP {
    interface View {
        fun isLoading(isLoading: Boolean)
        fun setData(any: Any)
        fun createRequestToken(token: String)
        fun createSession(sessionId: String)
        fun createSessionWithLogin(response: CreateRequestTokenResponse)
        fun accountDetail(accountDetailResponse: AccountDetailResponse)
        fun onError(message: String)
    }

    interface Presenter {
        fun createRequestToken()

        fun createSession()

        fun createSessionWithLogin(username: String, password: String)

        fun getAccountDetails()

        fun cancelRequest()

    }
}