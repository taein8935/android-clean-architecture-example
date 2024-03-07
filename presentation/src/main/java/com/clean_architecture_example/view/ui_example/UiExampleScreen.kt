package com.clean_architecture_example.view.ui_example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.clean_architecture_example.navigation.Page

@Composable
fun UiExampleScreen(
    mainNavController: NavHostController,
    viewModel: UiExampleViewModel
) {

    Column {
        Button(
            onClick = { mainNavController.navigate(Page.ViewPagerExample.route) },
            shape = RectangleShape,
        ) {
            Text(
                text = "1. Go ViewPager",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

