package com.example.movieapp.ui.main.movieDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.core.adapter.SimilarMoviesAdapter
import com.example.movieapp.core.app.App
import com.example.movieapp.core.database.RoomDB
import com.example.movieapp.core.model.databse.RoomModel
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.core.model.response.main.movieData.movieDetails.MovieDetailsResponse
import com.example.movieapp.core.model.response.main.movieTrailer.MovieTrailerResponse
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMoviesResponse
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.ui.base.BaseActivity
import com.example.movieapp.ui.main.home.homeFragment.HomeFragment
import com.example.movieapp.ui.main.trailer.TrailerActivity
import com.google.android.material.snackbar.Snackbar

class MovieDetailsActivity : BaseActivity(), MovieDetailsMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val KEY_LINK = "video_link"
    }

    var databse: RoomDB? = null

    lateinit var movieTrailerLink: String

    var isFavorite:Boolean?=null

    lateinit var movieDetailsResponse: MovieDetailsResponse

    lateinit var favoriteMoviesListRespond: List<MovieData>

    lateinit var data: MovieDetailsResponse

    lateinit var presenter: MovieDetailsMVP.Presenter

    lateinit var binding: ActivityMovieDetailsBinding

    var movieId:Int?=null

    private var productionCompanies: String = "Production companies: "
    lateinit var productionCountries: String
    var genre: String = "Genre: "

    lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_delete)
         val intent = intent
         val id = intent.getIntExtra(MOVIE_DATA, 0)

     }*/


    override fun getView(): View? {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        //databse= RoomDB.getInstance(this)

        binding.youtubeVideo.isClickable = true
        binding.playYoutubeVideo.isClickable = true

        val intent = intent
        val id = intent.getIntExtra(MOVIE_DATA, 0)
        //val id=AppCache.appCache!!.getMovieId()
        prepareSimilarMoviesRecyclerView()
        presenter = MovieDetailsPresenter(this)
        presenter.loadMovieDetails(id)
        presenter.loadSimilarMovies(id)
        similarMoviesClicked()

        binding.favoriteButtom.isClickable=false

        movieId=id

    }

    private fun setOnClickListenerActions(id: Int) {
        binding.openTrailer.setOnClickListener {
            binding.youtubeVideo.isClickable = false
            binding.playYoutubeVideo.isClickable = false
            presenter.loadMovieTrailer(id)
        }

        binding.playYoutubeVideo.setOnClickListener {
            binding.youtubeVideo.isClickable = false
            binding.playYoutubeVideo.isClickable = false
            presenter.loadMovieTrailer(id)
        }



        binding.favoriteButtom.setOnClickListener {

            if (isFavorite!!) {
                presenter.markAsNotFavorite(id)
                //presenter.loadFavoriteMovies()
                binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_false))
                isFavorite=false
            } else {
                presenter.markAsFavorite(id)
                //presenter.loadFavoriteMovies()
                binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_ture))
                isFavorite=true
            }


            /*run lit@{
                favoriteMoviesListRespond.forEach{
                    if (it.id == movieDetailsResponse.id) {
                        presenter.markAsFavorite(id)
                        binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_false))
                        return@lit
                    }
                }

                favoriteMoviesListRespond.forEach{
                    if (it.id != movieDetailsResponse.id) {
                        presenter.markAsFavorite(id)
                        binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_ture))
                        return@lit
                    }
                }
            }*/
        }

        /*run lit@{
            favoriteMoviesListRespond.forEach{
                if (it.id == movieDetailsResponse.id) {
                    binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_ture))
                    isFavorite=true
                    return@lit
                } else {
                    binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_false))
                    isFavorite=false
                }
            }
        }*/
    }

    fun similarMoviesClicked() {


        similarMoviesAdapter.onItemClicked = {

            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(HomeFragment.MOVIE_DATA, it.id)
            //val id = intent.getIntExtra(MOVIE_DATA, 0)
            //AppCache.appCache!!.setMovieId(it.id)
            startActivity(intent)
        }


    }


    private fun prepareSimilarMoviesRecyclerView() {
        similarMoviesAdapter = SimilarMoviesAdapter()
        binding.similarMovies.adapter = similarMoviesAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.similarMovies.layoutManager = layoutManager
    }

    override fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse) {
        similarMoviesAdapter.setData(similarMoviesResponse.results)
    }

    override fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse) {
        if (movieTrailerResponse.results.isNotEmpty()) {
            movieTrailerLink = movieTrailerResponse.results[0].key
            var intent = Intent(this, TrailerActivity::class.java)
            intent.putExtra(KEY_LINK, movieTrailerLink)
            startActivity(intent)
        } else {
            binding.youtubeVideo.visibility = View.GONE
        }

    }

    override fun getFavoriteMoviesList(favoriteMoviesListRespond: List<MovieData>) {

        this.favoriteMoviesListRespond=favoriteMoviesListRespond

        run lit@{
            favoriteMoviesListRespond.forEach{
                if (it.id == movieDetailsResponse.id) {
                    binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_ture))
                    isFavorite=true
                    return@lit
                } else {
                    isFavorite=false
                    binding.favoriteButtom.setColorFilter(ContextCompat.getColor(applicationContext,R.color.favorite_false))
                }
            }
        }
        binding.favoriteButtom.isClickable=true

        setOnClickListenerActions(movieId!!)
    }

    override fun getFavoriteResult(success: Boolean) {
        if (success) {
            Toast.makeText(
                applicationContext,
                "Marked as favorite successfully",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(applicationContext, "Not marked, error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showData(movieDetails: MovieDetailsResponse) {

        movieDetailsResponse = movieDetails


        presenter.loadFavoriteMovies()

        Glide.with(binding.movieImage)
            .load("https://image.tmdb.org/t/p/w500" + movieDetails.poster_path)
            .into(binding.movieImage)

        binding.releaseDate.text = "Release date: ${movieDetails.release_date}"

        binding.movieName.text = movieDetails.title

        movieDetails.production_companies.forEach {
            productionCompanies += "${it.name} "
        }
        binding.productionCompanies.text = productionCompanies

        binding.spokenLanguage.text = "Language: ${movieDetails.spoken_languages[0].name}"

        productionCountries = if (movieDetails.production_countries.size == 1) {
            "Production country: "
        } else {
            "Production countries: "
        }

        movieDetails.production_countries.forEach {
            var count = 0
            if (movieDetails.production_countries.size - 1 > count && movieDetails.production_countries.size > 0) {
                productionCountries += "${it.name}, "
                count++
            } else {
                productionCountries += "${it.name} "
            }
        }
        binding.countryName.text = productionCountries


        movieDetails.genres.forEach {
            genre += "${it.name} "
        }
        binding.movieGenre.text = genre

        binding.movieOverview.text = "Overview:\n ${movieDetails.overview}"

        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w500" + movieDetails.backdrop_path)
            .into(binding.youtubeVideoImage)

    }

    override fun setMovieDetails(movieDetails: MovieDetailsResponse) {
        data = movieDetails
        binding.progressBar.progress = (data.vote_average * 10).toInt()
        binding.progressBarText.text = ((data.vote_average * 10).toInt()).toString() + "%"
        showData(data)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }
}