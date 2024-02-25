package com.clean_architecture_data.api

import com.clean_architecture_data.model.MovieData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/movies?&_sort=category,id")
    suspend fun getMovies(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<MovieData>
    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") movieId: Int): MovieData
}