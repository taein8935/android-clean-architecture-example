package com.clean_architecture_example.view.movie.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import com.clean_architecture_domain.util.ApiResult
import com.clean_architecture_domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetail,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val movieId = mutableStateOf(savedStateHandle.get<Int>("movieId") ?: 466420)
    val movieData = MutableStateFlow<MovieEntity>(MovieEntity())

    init {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            getMovieById(movieId.value).onSuccess {
                movieData.value = it
            }
        }

    }
    private suspend fun getMovieById(movieId: Int): ApiResult<MovieEntity> {
        return getMovieDetail(movieId)
    }
}