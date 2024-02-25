package com.clean_architecture_data.repository.movie

import androidx.paging.PagingSource
import com.clean_architecture_data.model.MovieDbData
import com.clean_architecture_data.model.MovieRemoteKeyDbData
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult


interface MovieDataSource {
    interface Remote {
        suspend fun getMovies(page: Int, limit: Int): ApiResult<List<MovieEntity>>
        suspend fun getMovie(movieId: Int): ApiResult<MovieEntity>
    }

    interface Local {
        fun pagingSource(): PagingSource<Int, MovieDbData>
        suspend fun getMovies(): ApiResult<List<MovieEntity>>
        suspend fun saveMovies(movieEntities: List<MovieEntity>)
        suspend fun getLastRemoteKey(): MovieRemoteKeyDbData?
        suspend fun saveRemoteKey(key: MovieRemoteKeyDbData)
        suspend fun clearMovies()
        suspend fun clearRemoteKeys()
    }
}