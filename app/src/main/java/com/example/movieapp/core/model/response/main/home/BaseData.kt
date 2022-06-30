package com.example.movieapp.core.model.response.main.home

abstract class BaseData {
    companion object {
        val TYPE_SLIDER = 1
        val TYPE_NOW_PLAYING = 2
        val TYPE_POPULAR = 3
        val TYPE_TOP_RATED = 4
    }

    abstract fun getType():Int
}