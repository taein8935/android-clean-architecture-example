package com.clean_architecture_example.view.dialog.custom_checkbox_dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.clean_architecture_example.util.preview.PreviewContainer
import com.clean_architecture_example.view.dialog.custom_radio_button_dialog.RadioButtonState

@Composable
fun CustomCheckboxDialog(
    initialCheckboxList: ArrayList<CheckboxState>?,
    onClickCancel: () -> Unit,
    onClickConfirm: (ArrayList<CheckboxState>) -> Unit
) {
    val checkboxList = initialCheckboxList?.map {
        it.copy(
            text = it.text,
            isChecked = mutableStateOf(it.isChecked.value)
        )
    }?.let { ArrayList(it) } ?: ArrayList()

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

                Text(text = "추가 옵션을 선택해주세요.")

                Spacer(modifier = Modifier.height(15.dp))

                // checkbox list
                for (checkboxState in checkboxList) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                checkboxState.isChecked.value = !checkboxState.isChecked.value
                            },
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Checkbox(
                            checked = checkboxState.isChecked.value,
                            onCheckedChange = null
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = checkboxState.text,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                }

                Spacer(modifier = Modifier.height(15.dp))

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
                        onClickConfirm(checkboxList)
                    }) {
                        Text(text = "확인")
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CustomCheckboxDialogPreview() {
    CustomCheckboxDialog(
        initialCheckboxList = arrayListOf(
            CheckboxState(text = "1번"),
            CheckboxState(text = "2번"),
            CheckboxState(text = "3번"),
        ),
        onClickCancel = {},
        onClickConfirm = {},
    )
}