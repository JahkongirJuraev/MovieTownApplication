package com.example.movieapp.core.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.movieapp.BuildConfig
import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.database.RoomDB
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

        db = Room.databaseBuilder(applicationContext, RoomDB::class.java, "room_database.db").allowMainThreadQueries().fallbackToDestructiveMigration().build()


    }

    companion object {
        var instance: Application? = null
            private set

        var db: RoomDB? = null
    }
}