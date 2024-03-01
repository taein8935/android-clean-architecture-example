package com.clean_architecture_example.view.dialog.custom_text_field_dialog

data class CustomTextFieldDialogState(
    var text: String? = null,
    var isShowDialog: Boolean = false,
    val onClickConfirm: (text: String) -> Unit,
    val onClickCancel: () -> Unit,
)
