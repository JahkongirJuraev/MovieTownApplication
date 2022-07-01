package com.example.movieapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.extension.SetItemStatusBarColor
import com.example.movieapp.databinding.ActivityStartBinding
import com.example.movieapp.ui.base.BaseActivity
import com.example.movieapp.ui.login.LoggingActivity
import com.example.movieapp.ui.main.home.MainActivity
import java.util.*

class StartActivity : BaseActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun getView(): View? {
        binding = ActivityStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        SetItemStatusBarColor(getColor(R.color.black), false)
        var date = Date()

        Log.d("Test", "StartActivity")
        if (AppCache.appCache!!.isFirstOpen()) {
            val loginIntent = Intent(this, LoggingActivity::class.java)
            startActivity(loginIntent)
            finish()
        } else {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}