package com.example.movieapp.core.cache

import android.content.Context
import android.content.SharedPreferences

class AppCache private constructor(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("MovieAppCache", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = null

    private val IS_FIRST_OPEN = "IsFirstOpen"
    private val USER_LOGIN = "IsUserLogin"
    private val ACCESS_TOKEN = "Token"
    private val USER_SESSION_ID="SessionId"
    private val MOVIE_ID="MovieId"
    private val ACCOUNT_ID="AccountId"

    companion object {
        var appCache: AppCache? = null
        fun init(context: Context) {
            if (appCache == null) {
                appCache = AppCache(context)
            }
        }
    }

    fun isUserLogin():Boolean {
        return preferences.getBoolean(USER_LOGIN, false)
    }

    fun setUserLogin(isLogin: Boolean) {
        editor = preferences.edit()
        editor?.apply {
            putBoolean(USER_LOGIN, isLogin)
            apply()
        }
    }

    fun isFirstOpen(): Boolean {
        return preferences.getBoolean(IS_FIRST_OPEN,true)
    }

    fun setFirstOpen(isFirstOpen: Boolean) {
        editor = preferences.edit()
        editor?.apply{
            putBoolean(IS_FIRST_OPEN, isFirstOpen)
            apply()
        }

    }

    fun setAccessToken(token:String) {
        editor = preferences.edit()
        editor?.apply{
            putString(ACCESS_TOKEN, token)
            apply()
        }
    }

    fun getAccessToken(): String {
        return preferences.getString(ACCESS_TOKEN, "Token").toString()
    }

    fun setSessionId(sessionId: String) {
        editor = preferences.edit()
        editor?.apply{
            putString(USER_SESSION_ID, sessionId)
            apply()
        }
    }

    fun getSessionId(): String {
        return preferences.getString(USER_SESSION_ID,"session")!!
    }

    fun setMovieId(movieId:Int) {
        editor = preferences.edit()
        editor?.apply{
            putInt(MOVIE_ID,movieId)
            apply()
        }
    }

    fun getMovieId(): Int {
        return preferences.getInt(MOVIE_ID,1)!!
    }

    fun setAccountId(accountId: Int) {
        editor = preferences.edit()
        editor?.apply{
            putInt(ACCOUNT_ID, accountId)
            apply()
        }
    }

    fun getAccountId(): Int {
        return preferences.getInt(ACCOUNT_ID, 1)!!
    }

}