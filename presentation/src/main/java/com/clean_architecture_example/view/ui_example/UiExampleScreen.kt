package com.clean_architecture_example.view.ui_example

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.clean_architecture_example.navigation.Page

@Composable
fun UiExampleScreen(
    mainNavController: NavHostController,
    viewModel: UiExampleViewModel
) {

    val uiState by remember {
        viewModel.uiState
    }

    Log.e("test", "UiExampleScreen Composable")
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

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { mainNavController.navigate(Page.TabPagerExample.route) },
            shape = RectangleShape,
        ) {
            Text(
                text = "2. Go TabPager",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { mainNavController.navigate(Page.VerticalScrollViewExample.route) },
            shape = RectangleShape,
        ) {
            Text(
                text = "3. Go Vertical Scroll View",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { mainNavController.navigate(Page.VerticalRecyclerViewExample.route) },
            shape = RectangleShape,
        ) {
            Text(
                text = "4. Go Vertical Recycler View",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { mainNavController.navigate(Page.HorizontalScrollViewExample.route) },
            shape = RectangleShape,
        ) {
            Text(
                text = "5. Go Horizontal Scroll View",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.setUiState() },
            shape = RectangleShape,
        ) {
            Text(
                text = "toggle",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Text(
            text = if (uiState.isLoading) "loading.." else "not loading",
        )

        Spacer(modifier = Modifier.height(20.dp))


        TestComposable(uiState.error)
    }
}


@Composable
fun TestComposable(error: String) {
    Log.e("test", "Test Composable")
    Text(
        text = "Hello Android Compose ViewPager"
    )
    Text(
        text = error,
    )
}