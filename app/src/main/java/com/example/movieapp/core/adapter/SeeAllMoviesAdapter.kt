package com.example.movieapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.databinding.ItemInnerRecyclerviewBinding
import com.example.movieapp.databinding.ItemRecyclerviewBinding

class SeeAllMoviesAdapter : RecyclerView.Adapter<SeeAllMoviesAdapter.ViewHolder>() {

    private var data= ArrayList<MovieData>()

    var onItemClicked:((MovieData)->Unit)?=null


    fun setData(data: List<MovieData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: MovieData) {
            binding.movieName.text = movieData.title
            binding.progressBar.progress = (movieData.vote_average * 10).toInt()
            binding.progressBarText.text = (movieData.vote_average * 10).toString()
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500" + movieData.poster_path)
                .into(binding.innerRecyclerViewImage)

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}