package com.clean_architecture_example.view.dialog.custom_time_picker_dialog

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    selectedHour: Int?,
    selectedMinute: Int?,
    onClickCancel: () -> Unit,
    onClickConfirm: (hour: Int, minute: Int) -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour ?: 0,
        initialMinute = selectedMinute ?: 0,
        is24Hour = false
    )
    Dialog(
        onDismissRequest = { onClickCancel() },
    ) {
        Card(
            shape = RoundedCornerShape(8.dp), // Card의 모든 꼭지점에 8.dp의 둥근 모서리 적용
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(text = "시간을 선택해주세요.")

                Spacer(modifier = Modifier.width(15.dp))

                TimePicker(
                    state = timePickerState,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = {
                        onClickCancel()
                    }) {
                        Text(text = "취소")
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    Button(onClick = {
                        val hour = timePickerState.hour
                        val minute = timePickerState.minute
                        onClickConfirm(hour, minute)
                    }) {
                        Text(text = "확인")
                    }
                }
            }
        }
    }
}
