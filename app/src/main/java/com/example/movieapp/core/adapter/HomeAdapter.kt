package com.example.movieapp.core.adapter

import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.core.model.response.main.home.BaseData
import com.example.movieapp.core.model.response.main.home.latestMovies.MovieData
import com.example.movieapp.core.model.response.main.home.latestMovies.SliderResponse
import com.example.movieapp.core.model.response.main.home.nowPlayingMovie.NowPlayingResponse
import com.example.movieapp.core.model.response.main.home.popularMovies.PopularResponse
import com.example.movieapp.core.model.response.main.home.topRatedMovies.TopRatedResponse
import com.example.movieapp.databinding.HomeNowPlayingBinding
import com.example.movieapp.databinding.HomePopularBinding
import com.example.movieapp.databinding.HomeSliderBinding
import com.example.movieapp.databinding.HomeTopRatedBinding
import com.example.movieapp.ui.main.home.homeFragment.HomeFragment.Companion.handler
import java.util.logging.Handler
import kotlin.math.abs

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<BaseData>()

    var topRatedAdapter = TopRatedAdapter()
    var popularAdapter = PopularAdapter()

    var onSliderClicked: ((MovieData) -> Unit)? = null
    var onTopRatedClicked: ((MovieData) -> Unit)? = null
    var onNowPlayingClicked: ((MovieData) -> Unit)? = null
    var onPopularClciked: ((MovieData) -> Unit)? = null

    var onSeeAllTopRatedClicked: ((String) -> Unit)? = null
    var onSeeAllNowPlayingClicked: ((String) -> Unit)? = null
    var onSeeAllPopularClicked: ((String) -> Unit)? = null


    fun addData(response: BaseData) {
        data.add(response)
        val d = data.sortedBy {
            it.getType()
        }
        this.data.clear()
        this.data.addAll(d)
        notifyItemInserted(data.size - 1)
    }

    fun setData(response: ArrayList<BaseData>) {
        data.clear()
        data.addAll(response)
        notifyItemInserted(data.size - 1)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyItemRangeRemoved(0, data.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            BaseData.TYPE_SLIDER -> {
                TYPE_SLIDER_VH(
                    HomeSliderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            BaseData.TYPE_TOP_RATED -> {
                TYPE_TOP_RATED_VH(
                    HomeTopRatedBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }

            BaseData.TYPE_POPULAR -> {
                TYPE_POPULAR_VH(
                    HomePopularBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }

            else -> {
                TYPE_NOW_PLAYING_VH(
                    HomeNowPlayingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BaseData.TYPE_SLIDER -> {
                (holder as TYPE_SLIDER_VH)
                    .bindData((data[position] as SliderResponse).results)
                /*sliderAdapter.onItemClicked={movieData->
                    holder.itemView.setOnClickListener{
                        onSliderClicked.invoke(movieData)
                    }
                }*/

            }

            BaseData.TYPE_TOP_RATED -> {
                (holder as TYPE_TOP_RATED_VH)
                    .bindData((data[position] as TopRatedResponse).results)
            }

            BaseData.TYPE_POPULAR -> {
                (holder as TYPE_POPULAR_VH)
                    .bindData((data[position] as PopularResponse).results)
            }

            else -> {
                (holder as TYPE_NOW_PLAYING_VH)
                    .bindData((data[position] as NowPlayingResponse).results)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    inner class TYPE_SLIDER_VH(val binding: HomeSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapter: SliderAdapter
        lateinit var viewPager2: ViewPager2


        fun bindData(movieList: List<MovieData>) {
            viewPager2=binding.sliderHome
            adapter = SliderAdapter(binding.sliderHome)
            handler= android.os.Handler(Looper.myLooper()!!)
            binding.sliderHome.adapter = adapter
            adapter.setData(movieList)
            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 1
            viewPager2.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }

            viewPager2.setPageTransformer(transformer)

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(runnable)
                    handler.postDelayed(runnable,3000)
                }
            })



            adapter?.onItemClicked = {
                onSliderClicked?.invoke(it)
            }

        }



        private val runnable= Runnable {
            viewPager2.currentItem= viewPager2.currentItem+1
        }
    }

    inner class TYPE_NOW_PLAYING_VH(var binding: HomeNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var adapter = NowPlayingAdapter()
        fun bindData(movieList: List<MovieData>) {

            binding.innerRecycler.adapter = adapter
            val layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.innerRecycler.layoutManager = layoutManager
            adapter.setData(movieList)
            binding.movieType.text = "Now Playing Movies"

            adapter.onItemClicked = {
                onNowPlayingClicked!!.invoke(it)
            }
            binding.arrow.setOnClickListener {
                onSeeAllNowPlayingClicked!!.invoke("Now Playing Movies")
            }

        }
    }

    inner class TYPE_TOP_RATED_VH(var binding: HomeTopRatedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieList: List<MovieData>) {
            binding.innerRecycler.adapter = topRatedAdapter
            var layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.innerRecycler.layoutManager = layoutManager
            topRatedAdapter.setData(movieList)

            binding.movieType.text = "Top Rated Movies"



            topRatedAdapter.onItemClicked = {
                onTopRatedClicked?.invoke(it)
            }

            binding.arrow.setOnClickListener {
                onSeeAllTopRatedClicked!!.invoke("Top Rated Movies")
            }
        }
    }

    inner class TYPE_POPULAR_VH(var binding: HomePopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieList: List<MovieData>) {
            binding.innerRecycler.adapter = popularAdapter
            val layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.innerRecycler.layoutManager = layoutManager
            popularAdapter.setData(movieList)
            binding.movieType.text = "Popular Movies"

            popularAdapter.onItemClicked = {
                onPopularClciked!!.invoke(it)

            }

            binding.arrow.setOnClickListener {
                onSeeAllPopularClicked!!.invoke("Popular Movies")
            }
        }
    }


}