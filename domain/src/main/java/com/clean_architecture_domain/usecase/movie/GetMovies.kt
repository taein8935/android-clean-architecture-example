package com.clean_architecture_domain.usecase.movie

import androidx.paging.PagingData
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies (
    private val movieRepository: MovieRepository
){
    operator fun invoke(pageSize: Int): Flow<PagingData<MovieEntity>> = movieRepository.movies(pageSize)
}