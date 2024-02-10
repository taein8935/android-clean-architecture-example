package com.clean_architecture_data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val remote: MovieDataSource.Remote) : MovieRepository {
    override fun movies(pageSize: Int): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(remote) }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieEntity> =
        remote.getMovie(movieId)
}