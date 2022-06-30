package com.example.movieapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.databinding.ItemSliderBinding

class SliderAdapter(private val viewPager2:ViewPager2) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

     val data = ArrayList<MovieData>()

     var onItemClicked: ((MovieData) -> Unit)?=null


    fun setData(data: List<MovieData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: MovieData) {
            Glide.with(binding.sliderItemImage)
                .load("https://image.tmdb.org/t/p/w500" + movieData.backdrop_path)
                .into(binding.sliderItemImage)

            binding.sliderItemName.text = movieData.title
            binding.sliderItemGenre.text=movieData.original_title



            binding.root.setOnClickListener {
                onItemClicked?.invoke(movieData)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSliderBinding.inflate(
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
        if (position == data.size-1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private val runnable = Runnable{
        data.addAll(data)
        notifyDataSetChanged()
    }
}