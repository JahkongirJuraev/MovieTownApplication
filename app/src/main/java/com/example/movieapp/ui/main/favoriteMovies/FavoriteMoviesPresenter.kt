package com.example.movieapp.ui.main.favoriteMovies

import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.model.response.main.account.addwatchList.FavoriteMoviesRespond
import com.example.movieapp.core.network.ApiClientModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class FavoriteMoviesPresenter(val view: FavoriteMVP.View) : FavoriteMVP.Presenter {

    var favoriteMoviesService = ApiClientModule.getFavoriteMoviesService()
    var movieDetailsService=ApiClientModule.getMoviesData()
    var compositeDisposable = CompositeDisposable()

    override fun loadFavoriteMovies() {
        var disposable = favoriteMoviesService.getFavoriteMovies(
            accountId = AppCache.appCache!!.getAccountId(),
            apiKey = ApiClientModule.apiKey,
            sessionId = AppCache.appCache!!.getSessionId()
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<FavoriteMoviesRespond?>>() {
                override fun onSuccess(t: Response<FavoriteMoviesRespond?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.getFavoriteMovie(it)
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