package com.clean_architecture_domain.repository

import androidx.paging.PagingData
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun movies(pageSize: Int): Flow<PagingData<MovieEntity>>

    fun moviesUsingRemoteMediator(pageSize: Int): Flow<PagingData<MovieEntity>>
    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieEntity>
}