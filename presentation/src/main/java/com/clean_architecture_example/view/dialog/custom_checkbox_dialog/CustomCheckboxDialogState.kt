package com.clean_architecture_example.view.dialog.custom_checkbox_dialog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.clean_architecture_example.view.dialog.custom_radio_button_dialog.RadioButtonState

data class CustomCheckboxDialogState(
    var checkboxList: ArrayList<CheckboxState>? = null,
    var isShowDialog: Boolean = false,
    val onClickConfirm: (ArrayList<CheckboxState>) -> Unit,
    val onClickCancel: () -> Unit,
)

data class CheckboxState(
    val text: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false),
)
