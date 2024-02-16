package com.clean_architecture_data.db.movie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clean_architecture_data.model.MovieDbData
import com.clean_architecture_data.model.MovieRemoteKeyDbData


@Database(
    entities = [MovieDbData::class, MovieRemoteKeyDbData::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
}