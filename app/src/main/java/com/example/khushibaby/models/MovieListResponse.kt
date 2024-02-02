package com.example.khushibaby.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class MovieListResponse (
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Movie>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
)