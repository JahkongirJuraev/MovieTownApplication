package com.example.movieapp.ui.main.movieDetails

import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.model.request.markAsFavoriteRequest.MarkAsFavoriteRequest
import com.example.movieapp.core.model.response.main.account.favoriteMovies.FavoriteMoviesRespond
import com.example.movieapp.core.model.response.main.movieData.markAsFavorite.MarkAsFavoriteResponse
import com.example.movieapp.core.model.response.main.movieData.movieDetails.MovieDetailsResponse
import com.example.movieapp.core.model.response.main.movieTrailer.MovieTrailerResponse
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMoviesResponse
import com.example.movieapp.core.network.ApiClientModule
import com.example.movieapp.core.network.networkService.MovieDataServices
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MovieDetailsPresenter(val view: MovieDetailsMVP.View) : MovieDetailsMVP.Presenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var movieDetailsServer: MovieDataServices = ApiClientModule.getMoviesData()


    override fun loadMovieDetails(movieId: Int) {
        var disposable =
            movieDetailsServer.getMovieDetails(apiKey = ApiClientModule.apiKey, movieId = movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<MovieDetailsResponse?>>() {
                    override fun onSuccess(t: Response<MovieDetailsResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieDetails(it)

                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.onError(e.message.toString())
                    }

                })

        compositeDisposable.add(disposable)

    }

    override fun loadSimilarMovies(movieId: Int) {
        var disposable = movieDetailsServer.getSimilarMovies(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey,
            page = 1
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<SimilarMoviesResponse?>>() {
                override fun onSuccess(t: Response<SimilarMoviesResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setSimilarMovies(it)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun loadMovieTrailer(movieId: Int) {
        var disposable = movieDetailsServer.getMovieTrailer(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<MovieTrailerResponse?>>() {
                override fun onSuccess(t: Response<MovieTrailerResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.getMovieTrailer(it)
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

    override fun markAsFavorite(movieId: Int) {
        val body = MarkAsFavoriteRequest(true, media_id = movieId, "movie")
        var disposable = movieDetailsServer.markAsFavorite(
            accountId = AppCache.appCache!!.getAccountId(),
            apiKey = ApiClientModule.apiKey,
            sessionId = AppCache.appCache!!.getSessionId(),
            markAsFavoriteRequest = body
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
            object : DisposableSingleObserver<Response<MarkAsFavoriteResponse?>>() {
                override fun onSuccess(t: Response<MarkAsFavoriteResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.getFavoriteResult(true)
                        }
                    } else {
                        view.getFavoriteResult(false)
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun markAsNotFavorite(movieId: Int) {
        val body = MarkAsFavoriteRequest(false, media_id = movieId, "movie")
        var disposable = movieDetailsServer.markAsFavorite(
            accountId = AppCache.appCache!!.getAccountId(),
            apiKey = ApiClientModule.apiKey,
            sessionId = AppCache.appCache!!.getSessionId(),
            markAsFavoriteRequest = body
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
            object : DisposableSingleObserver<Response<MarkAsFavoriteResponse?>>() {
                override fun onSuccess(t: Response<MarkAsFavoriteResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.getFavoriteResult(true)
                        }
                    } else {
                        view.getFavoriteResult(false)
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.add(disposable)
    }

    override fun loadFavoriteMovies() {
        var disposable = movieDetailsServer.getFavoriteMovies(
            accountId = AppCache.appCache!!.getMovieId(),
            apiKey = ApiClientModule.apiKey,
            sessionId = AppCache.appCache!!.getSessionId()
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
            object : DisposableSingleObserver<Response<FavoriteMoviesRespond?>>() {
                override fun onSuccess(t: Response<FavoriteMoviesRespond?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.getFavoriteMoviesList(it.results)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })
        compositeDisposable.addAll(disposable)
    }


    override fun cancelRequest() {
        compositeDisposable.dispose()
    }


}
