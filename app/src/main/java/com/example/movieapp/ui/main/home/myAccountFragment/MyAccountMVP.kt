package com.example.movieapp.ui.main.home.myAccountFragment

import com.example.movieapp.core.model.response.main.account.accountDetails.AccountDetailsResponse

interface MyAccountMVP {

    interface View {
        fun setMyAccountData(myAccountDetailsResponse: AccountDetailsResponse)

        fun onError(message:String)

        fun isSessionDeleted(sessionDeleted:Boolean)


    }

    interface Presenter {

        fun loadMyAccountData()

        fun cancelRequest()

        fun deleteSession()

    }
}