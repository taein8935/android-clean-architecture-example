package com.clean_architecture_example.navigation

sealed class Page(val route: String) {

    // 메인
    data object Main: Page("main")
    data object MainPage: Page("mainPage")
    // 영화
    data object Movie: Page("movie")
    data object Movies: Page("movies")
    data object MovieDetail: Page("movie_detail")
    // UI_EXAMPLE
    data object UIExampleMain: Page("ui_example_main")
    data object UIExample: Page("ui_example")
    data object ViewPagerExample: Page("view_pager_example")
}



