package com.example.movieapp.core.model.databse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_database")
data class RoomModel(
    @PrimaryKey val movieId:Int,
    @ColumnInfo(name = "isFavorite", defaultValue = "false") val isFavorite:Boolean
)
{

    /* @PrimaryKey
     var movieId: Int = 0

     @ColumnInfo(name = "isFavorite")
     var isFavorite:Boolean =false*/

}