package com.clean_architecture_data.repository.movie

import androidx.paging.PagingSource
import com.clean_architecture_data.db.movie.MovieDao
import com.clean_architecture_data.db.movie.MovieRemoteKeyDao
import com.clean_architecture_data.mapper.toDbData
import com.clean_architecture_data.model.MovieDbData
import com.clean_architecture_data.model.MovieRemoteKeyDbData
import com.clean_architecture_data.model.toDomain
import com.clean_architecture_data.util.DiskExecutor
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieLocalDataSource(
    private val executor: DiskExecutor,
    private val movieDao: MovieDao,
    private val remoteKeyDao: MovieRemoteKeyDao,
) : MovieDataSource.Local {

    override fun pagingSource(): PagingSource<Int, MovieDbData> = movieDao.pagingSource()

    override suspend fun getMovies(): ApiResult<List<MovieEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val movies = movieDao.getMovies()
        return@withContext if (movies.isNotEmpty()) {
            ApiResult.Success(movies.map { it.toDomain() })
        } else {
            ApiResult.Error(Throwable("Data Not Available"))
        }
    }
    override suspend fun saveMovies(movieEntities: List<MovieEntity>) = withContext(executor.asCoroutineDispatcher()) {
        movieDao.saveMovies(movieEntities.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): MovieRemoteKeyDbData? = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: MovieRemoteKeyDbData) = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearMovies() = withContext(executor.asCoroutineDispatcher()) {
        movieDao.clearMoviesExceptFavorites()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.clearRemoteKeys()
    }
}