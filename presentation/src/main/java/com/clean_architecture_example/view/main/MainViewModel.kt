package com.clean_architecture_example.view.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.clean_architecture_example.view.dialog.custom_alert_dialog.CustomAlertDialogState
import com.clean_architecture_example.view.dialog.custom_bottom_sheet_dialog.CustomBottomSheetDialogState
import com.clean_architecture_example.view.dialog.custom_date_picker_dialog.CustomDatePickerDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle?,
) : ViewModel() {
    val customAlertDialogState: MutableState<CustomAlertDialogState> = mutableStateOf(
        CustomAlertDialogState()
    )
    val customBottomSheetDialogState: MutableState<CustomBottomSheetDialogState> = mutableStateOf(
        CustomBottomSheetDialogState()
    )
    val customDatePickerDialogState: MutableState<CustomDatePickerDialogState?> =
        mutableStateOf(null)


    init {
        customDatePickerDialogState.value = CustomDatePickerDialogState(
            onClickConfirm = { yyyyMMdd ->
                Log.e("test", "onclickConfirm")
                customDatePickerDialogState.value = customDatePickerDialogState.value?.copy(
                    isShowDialog = false,
                    selectedDate = yyyyMMdd
                )
            },
            onClickCancel = {
                customDatePickerDialogState.value = customDatePickerDialogState.value?.copy(
                    isShowDialog = false
                )
            }
        )
    }

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

    fun showBottomSheetDialog() {
        customBottomSheetDialogState.value = CustomBottomSheetDialogState(
            title = "국기 이모지",
            description = "나라별 국기 이모지를 확인해보세요.",
            onClickConfirm = {
                resetBottomSheetDialogState()
            },
            onClickCancel = {
                resetBottomSheetDialogState()
            }
        )
    }

    fun showDatePickerDialog() {
        Log.e("customDatePickerDialogState.value", customDatePickerDialogState.value.toString())
        customDatePickerDialogState.value =
            customDatePickerDialogState.value?.copy(isShowDialog = true)
    }


    // 다이얼로그 상태 초기화
    fun resetDialogState() {
        customAlertDialogState.value = CustomAlertDialogState()
    }

    fun resetBottomSheetDialogState() {
        customBottomSheetDialogState.value = CustomBottomSheetDialogState()
    }
}