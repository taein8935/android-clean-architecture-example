package com.clean_architecture_data.db.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clean_architecture_data.model.MovieRemoteKeyDbData

@Dao
interface MovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRemoteKey(keys: MovieRemoteKeyDbData)

    @Query("SELECT * FROM movies_remote_keys WHERE id=:id")
    suspend fun getRemoteKeyByMovieId(id: Int): MovieRemoteKeyDbData?

    @Query("DELETE FROM movies_remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT * FROM movies_remote_keys WHERE id = (SELECT MAX(id) FROM movies_remote_keys)")
    suspend fun getLastRemoteKey(): MovieRemoteKeyDbData?
}