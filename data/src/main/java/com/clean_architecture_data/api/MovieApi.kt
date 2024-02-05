package com.clean_architecture_data.api

import com.clean_architecture_data.model.MovieData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/movies")
    suspend fun getMovies(@Query("id") movieIds: List<Int>): List<MovieData>
    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") movieId: Int): MovieData
}