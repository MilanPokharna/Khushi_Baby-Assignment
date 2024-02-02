package com.example.khushibaby.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.khushibaby.api.RetrofitService
import com.example.khushibaby.db.MovieDatabase
import com.example.khushibaby.models.MovieListResponse
import com.example.khushibaby.paging.MoviePagingSource
import com.example.khushibaby.paging.MovieRemoteMediator
import com.example.khushibaby.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@ExperimentalPagingApi
class MainRepository @Inject constructor(
    val retrofitService: RetrofitService,
    val movieDatabase: MovieDatabase,
    @ApplicationContext context: Context
) {

    private val context = context


    fun getMovieList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = MovieRemoteMediator(retrofitService,movieDatabase),
        pagingSourceFactory = { movieDatabase.moviesDao().getMovieList() }
    ).liveData

}