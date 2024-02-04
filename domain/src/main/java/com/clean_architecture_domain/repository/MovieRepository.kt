package com.clean_architecture_domain.repository

import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult


interface MovieRepository {
    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieEntity>
}