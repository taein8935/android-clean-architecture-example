package com.clean_architecture_example.di

import com.clean_architecture_data.api.MovieApi
import com.clean_architecture_data.repository.movie.MovieDataSource
import com.clean_architecture_data.repository.movie.MovieRemoteDataSource
import com.clean_architecture_data.repository.movie.MovieRepositoryImpl
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote)
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
}