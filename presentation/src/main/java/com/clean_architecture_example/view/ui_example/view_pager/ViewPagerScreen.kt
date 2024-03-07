package com.clean_architecture_example.view.ui_example.view_pager

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun ViewPagerScreen(
    mainNavController: NavHostController,
    viewModel: ViewPagerViewModel
) {

    val pagerState = rememberPagerState {
        10
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Hello Android Compose ViewPager"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "pageCount : ${pagerState.pageCount}"
        )
        Text(
            text = "currentPage : ${pagerState.currentPage}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomViewPager(pagerState = pagerState)
    }
}

@Composable
fun CustomViewPager(pagerState: PagerState) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,

        state = pagerState
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center, // 세로 방향으로 가운데 정렬
                horizontalAlignment = Alignment.CenterHorizontally // 가로 방향으로 가운데 정렬
            ) {
                Text(
                    text = "Page $it",
                )
            }
        }
    }
}

@Composable
@Preview
fun CustomViewPagerPreview() {
    CustomViewPager(rememberPagerState { 10 })
}