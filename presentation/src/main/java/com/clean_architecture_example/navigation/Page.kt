package com.clean_architecture_example.navigation

sealed class Page(val route: String) {
    data object MoveDetail: Page("movie_detail")
}