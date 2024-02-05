package com.clean_architecture_data.repository.movie

import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.util.ApiResult

class MovieRepositoryImpl(private val remote: MovieDataSource.Remote) : MovieRepository {
    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieEntity> = remote.getMovie(movieId)
}