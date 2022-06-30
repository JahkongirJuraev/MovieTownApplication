package com.example.movieapp.ui.main.seeAllMovies

import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import com.example.movieapp.core.network.ApiClientModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class SeeAllMoviesPresenter(val view: SeeAllMoviesMVP.View) : SeeAllMoviesMVP.Presenter {

    var movieServer = ApiClientModule.getMoviesService()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadPopularMovies() {
        var disposable = movieServer.getPopularMoviesRequest(ApiClientModule.apiKey, 2)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<PopularResponse?>>() {
                    override fun onSuccess(t: Response<PopularResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setPopularMovies(it)
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

    override fun loadNowPlayingMovies() {
        var disposable = movieServer.getNowPlayingMoviesRequest(apiKey = ApiClientModule.apiKey, page = 1)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<NowPlayingResponse?>>() {
                    override fun onSuccess(t: Response<NowPlayingResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setNowPlayingMovies(it)
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

    override fun loadTopRatedMovies() {
        var disposable = movieServer.getTopRatedMoviesRequest(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
            object : DisposableSingleObserver<Response<TopRatedResponse?>>() {
                override fun onSuccess(t: Response<TopRatedResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setTopRatedMovies(it)
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