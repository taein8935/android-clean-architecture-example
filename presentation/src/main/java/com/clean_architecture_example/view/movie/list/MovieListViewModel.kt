package com.clean_architecture_example.view.movie.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import com.clean_architecture_domain.usecase.movie.GetMovies
import com.clean_architecture_domain.util.ApiResult
import com.clean_architecture_domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovies: GetMovies,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val movies = getMovies(20).cachedIn(viewModelScope)
}