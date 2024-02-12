package com.clean_architecture_data.repository.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.util.getResult


class MoviesPagingSource(
    private val remote: MovieDataSource.Remote
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: 1
        return remote.getMovies(page, params.loadSize).getResult({
            LoadResult.Page(
                data = it.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (it.data.isEmpty()) null else page + 1
            )
        }, {
            LoadResult.Error(it.error)
        })
    }
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}