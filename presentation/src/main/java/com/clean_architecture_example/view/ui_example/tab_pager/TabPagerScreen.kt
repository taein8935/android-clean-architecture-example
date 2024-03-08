package com.clean_architecture_example.view.ui_example.tab_pager

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TabPagerScreen(
    mainNavController: NavHostController,
    viewModel: TabPagerViewModel
) {
    val coroutineScope = rememberCoroutineScope() // 코루틴 스코프 생성
    val tabs = listOf("홈", "설정", "프로필")
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        // 탭 구현
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.Red, // 인디케이터 색상 변경
                )
            },
            divider = {}, // 빈 컴포저블을 지정하여 경계선 제거


            // 배경색 설정
            containerColor = Color.White,
            contentColor = Color.Red,
            // 에지 패딩 설정
            edgePadding = 0.dp,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                            //                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(), // 화면 전체 너비
            color = Color.LightGray, // 테두리 색상
            thickness = 1.dp // 테두리 두께
        )


        // 뷰페이저 구현
        HorizontalPager(
            state = pagerState
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 페이지별 컨텐츠
                when (page) {
                    0 -> HomeScreen()
                    1 -> SettingsScreen()
                    2 -> ProfileScreen()
                }
            }

        }
    }
}

@Composable
fun HomeScreen() {
    /* 홈 화면 구현 */
    Column {
        Text(text = "HomeScreen")
    }
}

@Composable
fun SettingsScreen() {
    /* 설정 화면 구현 */
    Column {
        Text(text = "SettingsScreen")
    }
}

@Composable
fun ProfileScreen() {
    /* 프로필 화면 구현 */
    Column {
        Text(text = "ProfileScreen")
    }
}

