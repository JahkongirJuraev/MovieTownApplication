package com.example.movieapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.core.model.databse.RoomModel

@Database(entities = [RoomModel::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    abstract fun getMainDao():MainDAO



}