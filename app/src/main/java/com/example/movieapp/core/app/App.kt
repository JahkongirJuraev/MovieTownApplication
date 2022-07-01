package com.example.movieapp.core.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.movieapp.BuildConfig
import com.example.movieapp.core.cache.AppCache
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCache.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    companion object {
        var instance: Application? = null
            private set

    }
}