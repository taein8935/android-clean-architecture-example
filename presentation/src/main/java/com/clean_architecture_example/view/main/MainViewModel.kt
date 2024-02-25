package com.clean_architecture_example.view.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.clean_architecture_example.view.dialog.CustomAlertDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle?,
) : ViewModel() {
    val customAlertDialogState: MutableState<CustomAlertDialogState> = mutableStateOf<CustomAlertDialogState>(
        CustomAlertDialogState()
    )
    fun showCustomAlertDialog() {
        customAlertDialogState.value = CustomAlertDialogState(
            title = "정말로 삭제하시겠습니까?",
            description = "삭제하면 복구할 수 없습니다.",
            onClickConfirm = {
                resetDialogState()
            },
            onClickCancel = {
                resetDialogState()
            }
        )
    }
    // 다이얼로그 상태 초기화
    fun resetDialogState() {
        customAlertDialogState.value = CustomAlertDialogState()
    }
}