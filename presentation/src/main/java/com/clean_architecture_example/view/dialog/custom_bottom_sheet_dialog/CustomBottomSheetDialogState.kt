package com.clean_architecture_example.view.dialog.custom_bottom_sheet_dialog

data class CustomBottomSheetDialogState(
    val title: String = "",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
