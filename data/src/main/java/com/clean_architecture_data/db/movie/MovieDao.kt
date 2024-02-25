package com.clean_architecture_data.db.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clean_architecture_data.model.MovieDbData

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY category, id")
    fun pagingSource(): PagingSource<Int, MovieDbData>
    @Query("SELECT * FROM movies ORDER BY category,id")
    fun getMovies(): List<MovieDbData>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MovieDbData>)
    @Query("DELETE FROM movies")
    suspend fun clearMoviesExceptFavorites()
}