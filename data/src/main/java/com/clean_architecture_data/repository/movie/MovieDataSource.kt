package com.clean_architecture_data.repository.movie

import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult


interface MovieDataSource {
    interface Remote {
        suspend fun getMovie(movieId: Int): ApiResult<MovieEntity>
    }
}