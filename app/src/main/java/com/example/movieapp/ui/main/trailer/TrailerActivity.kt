package com.example.movieapp.ui.main.trailer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.movieapp.core.utils.CONST
import com.example.movieapp.databinding.ActivityTrailerBinding
import com.example.movieapp.ui.base.BaseActivity
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
                    TODO("Not yet implemented")
                }

            })
        }
    }

}
