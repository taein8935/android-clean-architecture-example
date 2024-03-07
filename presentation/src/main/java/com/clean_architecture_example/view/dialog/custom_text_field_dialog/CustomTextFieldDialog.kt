package com.clean_architecture_example.view.dialog.custom_text_field_dialog

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomTextFieldDialog(
    initialText: String?,
    onClickCancel: () -> Unit,
    onClickConfirm: (text: String) -> Unit
) {

    val text = remember { mutableStateOf(initialText ?: "") }

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

                Text(text = "후기를 입력해주세요.")

                Spacer(modifier = Modifier.height(15.dp))

                // TextField
                BasicTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFFBAE5F5),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .padding(all = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "",
                                tint = Color.DarkGray,
                            )
                            Spacer(modifier = Modifier.width(width = 8.dp))
                            innerTextField()
                        }
                    },
                )

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
                        onClickConfirm(text.value)
                    }) {
                        Text(text = "확인")
                    }
                }
            }
        }
    }
}
