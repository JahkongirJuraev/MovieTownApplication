package com.example.movieapp.ui.main.home.homeFragment

import com.example.movieapp.core.model.response.main.home.latestMovies.SliderResponse
import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import com.example.movieapp.core.network.ApiClientModule
import com.example.movieapp.core.network.networkService.MovieService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class HomePresenter(val view: HomeMVP.View) : HomeMVP.Presenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var movieService: MovieService = ApiClientModule.getMoviesService()

    override fun loadSliderData() {
        var disposable = movieService.getSliderMovies(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<SliderResponse?>>() {
                override fun onSuccess(t: Response<SliderResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setMovieData(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })

        compositeDisposable.add(disposable)
    }

    /* val disposable = loginServices.createRequestToken(ApiClientModule.apiKey)
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
                 }*/
    override fun loadTopRated() {
        val disposable = movieService.getTopRatedMoviesRequest(ApiClientModule.apiKey,1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<TopRatedResponse?>>() {
                override fun onSuccess(t: Response<TopRatedResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setMovieData(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun loadPopular() {
        val disposable = movieService.getPopularMoviesRequest(ApiClientModule.apiKey,2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<PopularResponse?>>() {
                override fun onSuccess(t: Response<PopularResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setMovieData(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }


            })
        compositeDisposable.add(disposable)
    }

    override fun loadNowPlaying() {
        val disposable = movieService.getNowPlayingMoviesRequest(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<NowPlayingResponse?>>() {
                override fun onSuccess(t: Response<NowPlayingResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setMovieData(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString() )
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun cancelRequest() {
        compositeDisposable.dispose()
    }

}