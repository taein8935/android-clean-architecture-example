package com.clean_architecture_data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.clean_architecture_data.model.MovieDbData
import com.clean_architecture_data.model.MovieRemoteKeyDbData
import com.clean_architecture_domain.util.getResult

private const val MOVIE_START_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val local: MovieDataSource.Local, // 로컬 데이터 소스 참조
    private val remote: MovieDataSource.Remote // 원격 데이터 소스 참조
) : RemoteMediator<Int, MovieDbData>() { // RemoteMediator를 상속받아 구현
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieDbData>
    ): MediatorResult {
        // 페이지 로드 유형에 따라 로드할 페이지 번호 결정
        val page = when (loadType) {
            LoadType.REFRESH -> MOVIE_START_PAGE_INDEX // 새로고침 시, 첫 페이지부터 시작
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true) // 리스트 상단에 데이터 추가할 필요 없음
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(
                endOfPaginationReached = true
            ) // 다음 페이지 번호 또는 더 이상 로드할 페이지 없음
        }

        // 네트워크에서 영화 데이터 로드 시도
        return remote.getMovies(page, state.config.pageSize).getResult({ successResult ->
            // 새로고침 시 기존 데이터 및 키 삭제
            if (loadType == LoadType.REFRESH) {
                local.clearMovies()
                local.clearRemoteKeys()
            }

            // 로드한 영화 데이터
            val movies = successResult.data

            // 로드할 추가 데이터가 없는지 확인
            val endOfPaginationReached = movies.isEmpty()

            // 이전 페이지 및 다음 페이지 번호 계산
            val prevPage = if (page == MOVIE_START_PAGE_INDEX) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            // 로드한 영화 데이터 및 키를 로컬 데이터베이스에 저장
            local.saveMovies(movies)
            local.saveRemoteKey(MovieRemoteKeyDbData(prevPage = prevPage, nextPage = nextPage))

            // 데이터 로드 성공 결과 반환
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }, { errorResult ->
            // 데이터 로드 실패 결과 반환
            MediatorResult.Error(errorResult.error)
        })
    }
}