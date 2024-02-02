package com.example.khushibaby.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.khushibaby.models.Movie
import com.example.khushibaby.models.MovieListResponse
import com.example.khushibaby.models.MovieRemoteKeys
import com.example.khushibaby.utils.ListConverter

@Database(entities = [Movie::class,MovieRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun moviesDao() : MoviesDao

    abstract fun remoteKeyDao() : RemoteKeysDao

//    companion object{
//        @Volatile
//        private var INSTANCE : MovieDatabase? = null
//
//        fun getDatabase(context: Context): MovieDatabase{
//            if(INSTANCE == null){
//                synchronized(this){
//                    INSTANCE = Room.databaseBuilder(context,
//                        MovieDatabase::class.java,
//                        "moviesDB")
//                        .build()
//                }
//
//            }
//            return INSTANCE!!
//        }
//    }
}