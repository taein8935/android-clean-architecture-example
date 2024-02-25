package com.clean_architecture_data.repository.movie

import com.clean_architecture_data.api.MovieApi
import com.clean_architecture_data.model.toDomain
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.ApiResult

class MovieRemoteDataSource(private val movieApi: MovieApi) : MovieDataSource.Remote {
    override suspend fun getMovies(page: Int, limit: Int): ApiResult<List<MovieEntity>> = try {
        val result = movieApi.getMovies(
            page = page,
            limit = limit
        )
        ApiResult.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        ApiResult.Error(e)
    }

    override suspend fun getMovie(movieId: Int): ApiResult<MovieEntity> = try {
        // 외부 API를 통해 영화 정보를 조회
        val result = movieApi.getMovie(movieId)
        // 조회 성공 시, DTO에서 Domain 모델로 변환
        ApiResult.Success(result.toDomain())
    } catch (e: Exception) {
        // 에러 처리
        ApiResult.Error(e)
    }
}