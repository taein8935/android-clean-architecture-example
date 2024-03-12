package com.clean_architecture_example.view.ui_example.vertical_scroll_view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class VerticalScrollViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

}