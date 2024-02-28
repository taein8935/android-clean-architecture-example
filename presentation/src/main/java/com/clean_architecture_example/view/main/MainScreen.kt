package com.clean_architecture_example.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.clean_architecture_example.util.preview.PreviewContainer
import com.clean_architecture_example.view.dialog.custom_alert_dialog.CustomAlertDialog
import com.clean_architecture_example.view.dialog.custom_bottom_sheet_dialog.CustomBottomSheetDialog
import com.clean_architecture_example.view.dialog.custom_date_picker_dialog.CustomDatePickerDialog
import com.clean_architecture_example.view.dialog.custom_loading_dialog.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    viewModel: MainViewModel
) {

    val customAlertDialogState = viewModel.customAlertDialogState.value
    val customBottomSheetDialogState = viewModel.customBottomSheetDialogState.value
    val customDatePickerDialogState = viewModel.customDatePickerDialogState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "메인화면") },
                navigationIcon = {
                    IconButton(onClick = { mainNavController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(
                onClick = { viewModel.showCustomAlertDialog() },
                shape = RectangleShape,
            ) {
                Text(
                    text = "1. Show AlertDialog",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { viewModel.showBottomSheetDialog() },
                shape = RectangleShape,
            ) {
                Text(
                    text = "2. Show BottomSheetDialog",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { LoadingState.show() },
                shape = RectangleShape,
            ) {
                Text(
                    text = "3. Show LoadingDialog",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { viewModel.showDatePickerDialog() },
                shape = RectangleShape,
            ) {
                Text(
                    text = "4. Show DatePickerDialog",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                text = "Selected Date: ${viewModel.customDatePickerDialogState.value?.selectedDate ?: "날짜를 선택해주세요."}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            if (customAlertDialogState.title.isNotBlank()) {
                CustomAlertDialog(
                    title = customAlertDialogState.title,
                    description = customAlertDialogState.description,
                    onClickCancel = customAlertDialogState.onClickCancel,
                    onClickConfirm = customAlertDialogState.onClickConfirm
                )
            }

            if (customBottomSheetDialogState.title.isNotBlank()) {
                CustomBottomSheetDialog(
                    title = customBottomSheetDialogState.title,
                    description = customBottomSheetDialogState.description,
                    onClickCancel = customBottomSheetDialogState.onClickCancel,
                    onClickConfirm = customBottomSheetDialogState.onClickConfirm
                )
            }

            if (customDatePickerDialogState?.isShowDialog == true) {
                CustomDatePickerDialog(
                    selectedDate = customDatePickerDialogState.selectedDate,
                    onClickCancel = customDatePickerDialogState.onClickCancel,
                    onClickConfirm = customDatePickerDialogState.onClickConfirm
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() = PreviewContainer {
    val navController = rememberNavController()
    MainScreen(
        mainNavController = navController,
        viewModel = MainViewModel(null)
    )
}