package com.clean_architecture_example.view.dialog.custom_time_picker_dialog

data class CustomTimePickerDialogState(
    var selectedHour: Int? = null,
    var selectedMinute: Int? = null,
    var isShowDialog: Boolean = false,
    val onClickConfirm: (hour: Int, minute: Int) -> Unit,
    val onClickCancel: () -> Unit,
) {
    val selectedHHmm: String?
        get() {
            return if (selectedHour != null && selectedMinute != null) {
                val time = String.format("%02d%02d", selectedHour, selectedMinute)
                time
            } else {
                null
            }
        }
}
