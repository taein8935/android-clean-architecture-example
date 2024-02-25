package com.clean_architecture_data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.clean_architecture_data.model.toDomain
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.repository.MovieRepository
import com.clean_architecture_domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local,
    private val remoteMediator: MovieRemoteMediator
) : MovieRepository {

    /**
     * 네트워크 통신만을 사용하여 영화 목록 가져오기
     */
    override fun movies(pageSize: Int): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { MoviesPagingSource(remote) }
        ).flow
    }

    /**
     * 로컬 데이터베이스와 네트워크 통신을 사용하여 영화 목록 가져오기
     */
    @OptIn(ExperimentalPagingApi::class)
    override fun moviesUsingRemoteMediator(pageSize: Int): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { local.pagingSource() },
            remoteMediator = remoteMediator
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieEntity> =
        remote.getMovie(movieId)
}