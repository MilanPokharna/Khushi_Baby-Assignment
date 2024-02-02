package com.example.khushibaby.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.khushibaby.models.MovieRemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MovieRemoteKeys>)

    @Query("SELECT * FROM RemoteKeys where movieId=:id ")
    suspend fun getRemoteKeys(id: String) : MovieRemoteKeys

    @Query("Delete from RemoteKeys")
    suspend fun deleteRemoteKeys()

}