package com.clean_architecture_example.di

import com.clean_architecture_data.api.MovieApi
import com.clean_architecture_data.db.movie.MovieDao
import com.clean_architecture_data.db.movie.MovieRemoteKeyDao
import com.clean_architecture_data.repository.movie.MovieDataSource
import com.clean_architecture_data.repository.movie.MovieLocalDataSource
import com.clean_architecture_data.repository.movie.MovieRemoteDataSource
import com.clean_architecture_data.repository.movie.MovieRemoteMediator
import com.clean_architecture_data.repository.movie.MovieRepositoryImpl
import com.clean_architecture_data.util.DiskExecutor
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import com.clean_architecture_domain.usecase.movie.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local,
        movieRemoteMediator: MovieRemoteMediator
    ): MovieRepository {
        return MovieRepositoryImpl(
            remote = movieRemote,
            local = movieLocal,
            remoteMediator = movieRemoteMediator
        )
    }

    @Provides
    @Singleton
    fun provideMovieMediator(
        movieLocalDataSource: MovieDataSource.Local,
        movieRemoteDataSource: MovieDataSource.Remote
    ): MovieRemoteMediator {
        return MovieRemoteMediator(movieLocalDataSource, movieRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor,
        movieDao: MovieDao,
        movieRemoteKeyDao: MovieRemoteKeyDao,
    ): MovieDataSource.Local {
        return MovieLocalDataSource(executor, movieDao, movieRemoteKeyDao)
    }


    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        movieApi: MovieApi
    ): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi)
    }

    @Provides
    fun provideGetMovieDetailUseCase(movieRepository: MovieRepository): GetMovieDetail {
        return GetMovieDetail(movieRepository)
    }

    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository): GetMovies {
        return GetMovies(movieRepository)
    }
}