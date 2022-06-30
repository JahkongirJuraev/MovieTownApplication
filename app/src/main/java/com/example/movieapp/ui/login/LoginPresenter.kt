package com.example.movieapp.ui.login

import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.model.request.createSessionRequest.CreateSessionRequest
import com.example.movieapp.core.model.request.createSessionWithLogin.CreateSessionWithLoginRequest
import com.example.movieapp.core.model.response.login.createRequestToken.CreateRequestTokenResponse
import com.example.movieapp.core.model.response.login.createSession.CreateSessionResponse
import com.example.movieapp.core.network.ApiClientModule
import com.example.movieapp.core.network.networkService.LoginServices
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail.AccountDetailResponse

class LoginPresenter(val view: LoginMVP.View) : LoginMVP.Presenter {

    var loginServices: LoginServices = ApiClientModule.getLoginService()

    private val compositeDisposable = CompositeDisposable()


    override fun createRequestToken() {
        val disposable = loginServices.createRequestToken(ApiClientModule.apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<CreateRequestTokenResponse?>>() {
                override fun onSuccess(t: Response<CreateRequestTokenResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache?.setAccessToken(it.request_token)
                            view.createRequestToken(it.request_token)
                        }
                    } else {
                        view.onError(t.message())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun createSessionWithLogin(username: String, password: String) {

        val body = CreateSessionWithLoginRequest(
            username = username,
            password = password,
            request_token = AppCache.appCache!!.getAccessToken()
        )

        val disposable = loginServices.createSessionWithLogin(
            apiKey = ApiClientModule.apiKey,
            createSessionWithLoginRequest = body
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<CreateRequestTokenResponse?>>() {
                override fun onSuccess(t: Response<CreateRequestTokenResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache!!.setAccessToken(it.request_token)
                            view.createSessionWithLogin(it)
                            AppCache.appCache!!.setAccessToken(it.request_token)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun createSession() {
        val body = CreateSessionRequest(request_token = AppCache.appCache!!.getAccessToken())
        val disposable = loginServices.createSession(
            createSessionRequest = body,
            apiKey = ApiClientModule.apiKey
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<CreateSessionResponse?>>() {
                override fun onSuccess(t: Response<CreateSessionResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache!!.setSessionId(it.session_id)
                            view.createSession(it.session_id)
                            AppCache.appCache!!.setSessionId(it.session_id)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun getAccountDetails() {
        val disposable = loginServices.getAccountDetails(
            apiKey = ApiClientModule.apiKey,
            sessionId = AppCache.appCache!!.getSessionId()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<AccountDetailResponse?>>() {
                override fun onSuccess(t: Response<AccountDetailResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.accountDetail(it)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun cancelRequest() {
        compositeDisposable.dispose()
    }


}