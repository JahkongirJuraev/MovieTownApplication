package com.example.movieapp.ui.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.movieapp.R
import com.example.movieapp.core.extension.SetItemStatusBarColor

abstract class BaseActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SetItemStatusBarColor(getColor(R.color.white),true)
        getView()?.let {
            setContentView(it)
        }

        Log.d("Test","BaseActivity")

        onCreated(savedInstanceState)

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        SetItemStatusBarColor(getColor(R.color.black),false)

    }

    abstract fun getView(): View?

    abstract fun onCreated(savedInstanceState:Bundle?)
}