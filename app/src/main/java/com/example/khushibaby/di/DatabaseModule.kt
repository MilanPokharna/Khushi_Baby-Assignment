package com.example.khushibaby.di

import android.content.Context
import androidx.room.Room
import com.example.khushibaby.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDB(@ApplicationContext context : Context) : MovieDatabase{
        return Room.databaseBuilder(context, MovieDatabase::class.java, "moviesDB").build()
    }


}