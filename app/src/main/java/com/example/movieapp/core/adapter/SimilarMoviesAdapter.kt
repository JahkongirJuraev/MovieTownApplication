package com.example.movieapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.model.response.main.similarMovies.SimilarMovieData
import com.example.movieapp.databinding.ItemInnerRecyclerviewBinding
import com.example.movieapp.databinding.ItemSliderBinding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

     val data = ArrayList<SimilarMovieData>()

     var onItemClicked: ((SimilarMovieData) -> Unit)?=null



    fun setData(data: List<SimilarMovieData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemInnerRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(similarMovieData: SimilarMovieData) {
            Glide.with(binding.innerRecyclerViewImage)
                .load("https://image.tmdb.org/t/p/w500" + similarMovieData.poster_path)
                .into(binding.innerRecyclerViewImage)

            binding.movieName.text = similarMovieData.title

            binding.root.setOnClickListener {
                onItemClicked?.invoke(similarMovieData)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInnerRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        /*holder.itemView.setOnClickListener {
            onItemClicked!!.invoke(data[position])
        }*/
    }

    override fun getItemCount(): Int {
        return data.size
    }
}