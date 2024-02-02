package com.example.khushibaby.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.khushibaby.models.Movie
import com.example.khushibaby.models.MovieListResponse

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie ")
    fun getMovieList() : PagingSource<Int,Movie>

    @Query("DELETE From Movie")
    suspend fun deleteAllMovies()

}