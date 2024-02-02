package com.example.khushibaby.api

import com.example.khushibaby.models.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie/popular?language=en-US&")
    suspend fun getMovieList(@Query ("page") pageIndex:Int) : Response<MovieListResponse>

}