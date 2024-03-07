package com.clean_architecture_example.view.ui_example.view_pager

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_domain.usecase.movie.GetMovieDetail
import com.clean_architecture_domain.usecase.movie.GetMovies
import com.clean_architecture_domain.util.ApiResult
import com.clean_architecture_domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class ViewPagerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

}