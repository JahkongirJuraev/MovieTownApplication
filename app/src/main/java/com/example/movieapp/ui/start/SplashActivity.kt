package com.example.movieapp.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.core.extension.SetItemStatusBarColor
import com.example.movieapp.ui.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun getView(): View? {
        return null
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        SetItemStatusBarColor(getColor(R.color.black),false)
        Log.d("Test","SplashActivity")
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }
}