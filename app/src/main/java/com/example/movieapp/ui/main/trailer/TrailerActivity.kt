package com.example.movieapp.ui.main.trailer

import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.core.extension.SetItemStatusBarColor
import com.example.movieapp.core.utils.CONST
import com.example.movieapp.databinding.ActivityTrailerBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class TrailerActivity : YouTubeBaseActivity() {

    companion object{
        val KEY_LINK="video_link"
    }

    private lateinit var binding:ActivityTrailerBinding

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SetItemStatusBarColor(getColor(R.color.black),false)

        intent.extras?.let {
            val link = it.getString(KEY_LINK)
            binding.playerView.initialize(CONST.DEVELOPER_KEY, object :
                YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(link)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                }

            })
        }
    }

}
