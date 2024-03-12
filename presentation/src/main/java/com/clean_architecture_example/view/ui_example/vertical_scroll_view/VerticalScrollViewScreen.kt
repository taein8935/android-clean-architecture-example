package com.clean_architecture_example.view.ui_example.vertical_scroll_view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun VerticalScrollViewScreen(
    mainNavController: NavHostController,
    viewModel: VerticalScrollViewViewModel
) {
    val scrollState = rememberScrollState()
    val items = List(5000) { it.toString() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "item's count : ${items.size}",
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomVerticalScrollView(
            items = items,
            scrollState = scrollState
        )
    }
}

@Composable
fun CustomVerticalScrollView(items: List<String>, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp) // 여기에 간격 지정
    ) {
        for (item in items) {
            CustomCardView(item)
        }
    }
}

@Composable
fun CustomCardView(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF5F5F),
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                textAlign = TextAlign.Center,
                text = "CardView $text"
            )
        }
    }
}


@Composable
@Preview
fun ScrollViewScreenPreview() {
    CustomVerticalScrollView(items = List(50) { it.toString() }, scrollState = ScrollState(3))
}