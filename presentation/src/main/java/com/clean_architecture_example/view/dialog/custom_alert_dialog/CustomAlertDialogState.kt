package com.clean_architecture_example.view.dialog.custom_alert_dialog

data class CustomAlertDialogState(
    val title: String = "",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
