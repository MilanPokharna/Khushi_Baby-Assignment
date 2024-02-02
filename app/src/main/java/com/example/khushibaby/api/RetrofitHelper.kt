package com.example.khushibaby.api

import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitHelper {
    const val APP_KEY = "d1f72e1eb2bc434a80c6f0172977f184"
    private const val BASE_URL = "https://api.themoviedb.org/3/"



    fun getInstance() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Chain): Response {
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOTI2MTFiN2Y1Y2NiNTFjMDc4NzU1OGIzMGUwMzlkOCIsInN1YiI6IjY1YjllNDJlNWJlMDBlMDE4MjVjMDU5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IxDX3E4g0aHuz3bVyEgBAxpYHjObc_Uc6T95LSnB-OE")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}