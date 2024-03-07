package com.clean_architecture_example.view.dialog.custom_radio_button_dialog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CustomRadioButtonDialogState(
    var radioButtonList: ArrayList<RadioButtonState>? = null,
    var isShowDialog: Boolean = false,
    val onClickConfirm: (ArrayList<RadioButtonState>) -> Unit,
    val onClickCancel: () -> Unit,
)
data class RadioButtonState(
    val text: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false),
)
