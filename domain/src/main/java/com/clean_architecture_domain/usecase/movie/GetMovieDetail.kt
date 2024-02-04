package com.clean_architecture_domain.usecase.movie

import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.util.ApiResult


class GetMovieDetail(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): ApiResult<MovieEntity> = movieRepository.getMovieDetail(movieId)
}
