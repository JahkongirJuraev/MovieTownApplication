package com.example.movieapp.ui.main.home.myAccountFragment

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.model.request.deleteSession.DeleteSessionRequest
import com.example.movieapp.core.model.request.markAsFavoriteRequest.MarkAsFavoriteRequest
import com.example.movieapp.core.model.response.main.account.accountDetails.AccountDetailsResponse
import com.example.movieapp.core.model.response.main.account.deleteSession.DeleteSessionRespond
import com.example.movieapp.core.model.response.main.movieData.markAsFavorite.MarkAsFavoriteResponse
import com.example.movieapp.core.network.ApiClientModule
import com.example.movieapp.core.network.networkService.AccountDataService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MyAccountPresenter(val view: MyAccountMVP.View) : MyAccountMVP.Presenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var myAccountService: AccountDataService = ApiClientModule.getAccountDataService()

    override fun loadMyAccountData() {
        var disposable =
            myAccountService.getAccountData(BuildConfig.apiKey, AppCache.appCache!!.getSessionId())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableSingleObserver<Response<AccountDetailsResponse?>>() {
                        override fun onSuccess(t: Response<AccountDetailsResponse?>) {
                            if (t.isSuccessful) {
                                t.body()?.let {
                                    AppCache.appCache!!.setAccountId(it.id)
                                    view.setMyAccountData(it)
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

    override fun deleteSession() {

        val body: DeleteSessionRequest =
            DeleteSessionRequest(session_id = AppCache.appCache!!.getSessionId())

        var disposable =
            myAccountService.deleteSession(apiKey = BuildConfig.apiKey, deleteSessionRequest = body)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableSingleObserver<Response<DeleteSessionRespond?>>() {
                        override fun onSuccess(t: Response<DeleteSessionRespond?>) {
                            if (t.isSuccessful) {
                                t.body()?.let {
                                    view.isSessionDeleted(it.success)
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
