package com.example.khushibaby.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeys")
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val movieId : String,
    val prevPage: Int?,
    val nextPage: Int?
)
