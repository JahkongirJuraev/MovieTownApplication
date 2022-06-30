package com.example.movieapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.databinding.ItemInnerRecyclerviewBinding

class NowPlayingAdapter:RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    var data = ArrayList<MovieData>()

    var onItemClicked: ((MovieData) -> Unit)? = null

    fun setData(data: List<MovieData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemInnerRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: MovieData) {
            Glide.with(binding.innerRecyclerViewImage)
                .load("https://image.tmdb.org/t/p/w500" + movieData.poster_path)
                .into(binding.innerRecyclerViewImage)
            binding.movieName.text = movieData.title

            binding.progressBar.progress= (movieData.vote_average*10).toInt()
            binding.progressBarText.text=((movieData.vote_average*10).toInt()).toString()+"%"

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemInnerRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}