package com.clean_architecture_example.view.dialog.custom_date_picker_dialog

data class CustomDatePickerDialogState(
    var selectedDate: String? = null,
    var isShowDialog: Boolean = false,
    val onClickConfirm: (yyyyMMdd: String) -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
