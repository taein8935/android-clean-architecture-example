package com.clean_architecture_example.view.movie.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetail,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val movieId = mutableStateOf(savedStateHandle.get<Int>("movieId") ?: 0)
    init {

    }


}