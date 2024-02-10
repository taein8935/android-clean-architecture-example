package com.clean_architecture_example.navigation

sealed class Page(val route: String) {
    data object Movies: Page("movies")
    data object MovieDetail: Page("movie_detail")
}