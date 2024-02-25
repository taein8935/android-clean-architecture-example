package com.clean_architecture_example.view.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.clean_architecture_example.navigation.Page
import com.clean_architecture_example.view.movie.detail.MovieDetailPage
import com.clean_architecture_example.view.movie.detail.MovieDetailViewModel
import com.clean_architecture_example.view.movie.list.MovieListPage
import com.clean_architecture_example.view.movie.list.MovieListScreen
import com.clean_architecture_example.view.movie.list.MovieListViewModel

@Composable
fun MainGraph(
    mainNavController: NavHostController
) {
    NavHost(navController = mainNavController, startDestination = Page.Movies.route) {
        composable(
            route = Page.Movies.route
        ) {
            val viewModel = hiltViewModel<MovieListViewModel>()
            MovieListPage(
                mainNavController = mainNavController,
                viewModel = viewModel
            )
        }

        composable(
            route = "${Page.MovieDetail.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            })
        ) {
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetailPage(
                mainNavController = mainNavController,
                viewModel = viewModel
            )
        }
    }
}