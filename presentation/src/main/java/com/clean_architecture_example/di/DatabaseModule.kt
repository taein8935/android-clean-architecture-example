package com.clean_architecture_example.di;

import android.content.Context;
import androidx.room.Room
import com.clean_architecture_data.db.movie.MovieDao
import com.clean_architecture_data.db.movie.MovieDatabase
import com.clean_architecture_data.db.movie.MovieRemoteKeyDao

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db").build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Provides
    fun provideMovieRemoteKeyDao(movieDatabase: MovieDatabase): MovieRemoteKeyDao {
        return movieDatabase.movieRemoteKeyDao()
    }
}
