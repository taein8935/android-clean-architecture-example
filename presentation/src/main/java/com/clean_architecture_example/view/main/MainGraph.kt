package com.clean_architecture_example.view.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clean_architecture_example.navigation.Page
import com.clean_architecture_example.view.movie.detail.MovieDetailScreen
import com.clean_architecture_example.view.movie.detail.MovieDetailViewModel

@Composable
fun MainGraph(
    mainNavController: NavHostController
) {
    NavHost(navController = mainNavController, startDestination = Page.MoveDetail.route) {
        composable(
            route = Page.MoveDetail.route
        ) {
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetailScreen(
                mainNavController = mainNavController,
                viewModel = viewModel
            )
        }
    }
}