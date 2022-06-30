package com.example.movieapp.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movieapp.core.model.databse.RoomModel

@Dao
interface MainDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomModel: RoomModel)

    @Query("UPDATE room_database SET isFavorite=:isFavorite WHERE movieId=:id")
    fun update(id: Int, isFavorite: Boolean)

    @Query("SELECT isFavorite FROM room_database WHERE movieId=:id")
    fun getMovieInfo(id:Int):Boolean


}