package com.clean_architecture_example.view.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.clean_architecture_example.view.dialog.custom_alert_dialog.CustomAlertDialogState
import com.clean_architecture_example.view.dialog.custom_bottom_sheet_dialog.CustomBottomSheetDialogState
import com.clean_architecture_example.view.dialog.custom_checkbox_dialog.CheckboxState
import com.clean_architecture_example.view.dialog.custom_checkbox_dialog.CustomCheckboxDialogState
import com.clean_architecture_example.view.dialog.custom_date_picker_dialog.CustomDatePickerDialogState
import com.clean_architecture_example.view.dialog.custom_radio_button_dialog.CustomRadioButtonDialogState
import com.clean_architecture_example.view.dialog.custom_radio_button_dialog.RadioButtonState
import com.clean_architecture_example.view.dialog.custom_text_field_dialog.CustomTextFieldDialogState
import com.clean_architecture_example.view.dialog.custom_time_picker_dialog.CustomTimePickerDialogState
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

    val customTimePickerDialogState: MutableState<CustomTimePickerDialogState?> =
        mutableStateOf(null)

    val customTextFieldDialogState: MutableState<CustomTextFieldDialogState?> =
        mutableStateOf(null)

    val customCheckboxDialogState: MutableState<CustomCheckboxDialogState?> =
        mutableStateOf(null)

    val customRadioButtonDialogState: MutableState<CustomRadioButtonDialogState?> =
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

        customTimePickerDialogState.value = CustomTimePickerDialogState(
            onClickConfirm = { hour, minute ->
                customTimePickerDialogState.value = customTimePickerDialogState.value?.copy(
                    isShowDialog = false,
                    selectedHour = hour,
                    selectedMinute = minute
                )
            },
            onClickCancel = {
                customTimePickerDialogState.value = customTimePickerDialogState.value?.copy(
                    isShowDialog = false
                )
            }
        )

        customTextFieldDialogState.value = CustomTextFieldDialogState(
            onClickConfirm = { text ->
                customTextFieldDialogState.value = customTextFieldDialogState.value?.copy(
                    isShowDialog = false,
                    text = text
                )
            },
            onClickCancel = {
                customTextFieldDialogState.value = customTextFieldDialogState.value?.copy(
                    isShowDialog = false
                )
            }
        )

        customCheckboxDialogState.value = CustomCheckboxDialogState(
            checkboxList = arrayListOf(
                CheckboxState("치킨무", mutableStateOf(false)),
                CheckboxState("어니언 양파링", mutableStateOf(false)),
                CheckboxState("치즈스틱", mutableStateOf(false)),
                CheckboxState("떡볶이", mutableStateOf(false)),
                CheckboxState("소떡소떡", mutableStateOf(false)),
            ),
            onClickConfirm = { checkboxList ->
                customCheckboxDialogState.value = customCheckboxDialogState.value?.copy(
                    isShowDialog = false,
                    checkboxList = checkboxList
                )
            },
            onClickCancel = {
                customCheckboxDialogState.value = customCheckboxDialogState.value?.copy(
                    isShowDialog = false
                )
            }
        )

        customRadioButtonDialogState.value = CustomRadioButtonDialogState(
            radioButtonList = arrayListOf(
                RadioButtonState("아주좋음", mutableStateOf(false)),
                RadioButtonState("좋음", mutableStateOf(false)),
                RadioButtonState("보통", mutableStateOf(false)),
                RadioButtonState("약간", mutableStateOf(false)),
                RadioButtonState("아주약간", mutableStateOf(false)),
            ),
            onClickConfirm = { radioButtonList ->
                customRadioButtonDialogState.value = customRadioButtonDialogState.value?.copy(
                    isShowDialog = false,
                    radioButtonList = radioButtonList
                )
            },
            onClickCancel = {
                customRadioButtonDialogState.value = customRadioButtonDialogState.value?.copy(
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
        customDatePickerDialogState.value =
            customDatePickerDialogState.value?.copy(isShowDialog = true)
    }

    fun showTimePickerDialog() {
        customTimePickerDialogState.value =
            customTimePickerDialogState.value?.copy(isShowDialog = true)
    }

    fun showTextFieldDialog() {
        customTextFieldDialogState.value =
            customTextFieldDialogState.value?.copy(isShowDialog = true)
    }

    fun showCheckboxDialog() {
        customCheckboxDialogState.value =
            customCheckboxDialogState.value?.copy(isShowDialog = true)
    }

    fun showRadioButtonDialog() {
        customRadioButtonDialogState.value =
            customRadioButtonDialogState.value?.copy(isShowDialog = true)
    }

    // 다이얼로그 상태 초기화
    fun resetDialogState() {
        customAlertDialogState.value = CustomAlertDialogState()
    }

    fun resetBottomSheetDialogState() {
        customBottomSheetDialogState.value = CustomBottomSheetDialogState()
    }
}