package com.clean_architecture_example.view.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.clean_architecture_example.navigation.Page
import com.clean_architecture_example.view.movie.detail.MovieDetailPage
import com.clean_architecture_example.view.movie.detail.MovieDetailViewModel
import com.clean_architecture_example.view.movie.list.MovieListPage
import com.clean_architecture_example.view.movie.list.MovieListViewModel
import com.clean_architecture_example.view.ui_example.UiExampleScreen
import com.clean_architecture_example.view.ui_example.UiExampleViewModel
import com.clean_architecture_example.view.ui_example.vertical_scroll_view.VerticalScrollViewViewModel
import com.clean_architecture_example.view.ui_example.horizontal_scroll_view.HorizontalScrollViewScreen
import com.clean_architecture_example.view.ui_example.horizontal_scroll_view.HorizontalScrollViewViewModel
import com.clean_architecture_example.view.ui_example.tab_pager.TabPagerScreen
import com.clean_architecture_example.view.ui_example.tab_pager.TabPagerViewModel
import com.clean_architecture_example.view.ui_example.vertical_recycler_view.VerticalRecyclerViewScreen
import com.clean_architecture_example.view.ui_example.vertical_recycler_view.VerticalRecyclerViewViewModel
import com.clean_architecture_example.view.ui_example.vertical_scroll_view.VerticalScrollViewScreen
import com.clean_architecture_example.view.ui_example.view_pager.ViewPagerScreen
import com.clean_architecture_example.view.ui_example.view_pager.ViewPagerViewModel

@Composable
fun MainGraph(
    mainNavController: NavHostController
) {
    NavHost(navController = mainNavController, startDestination = Page.Main.route) {


        // MAIN
        navigation(
            route = Page.Main.route,
            startDestination = Page.MainPage.route
        ) {
            composable(
                route = Page.MainPage.route
            ) {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }
        }

        // UI EXAMPLE
        navigation(
            route = Page.UIExampleMain.route,
            startDestination = Page.UIExample.route
        ) {
            composable(
                route = Page.UIExample.route
            ) {
                val viewModel = hiltViewModel<UiExampleViewModel>()
                UiExampleScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Page.ViewPagerExample.route
            ) {
                val viewModel = hiltViewModel<ViewPagerViewModel>()
                ViewPagerScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Page.TabPagerExample.route
            ) {
                val viewModel = hiltViewModel<TabPagerViewModel>()
                TabPagerScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Page.VerticalScrollViewExample.route
            ) {
                val viewModel = hiltViewModel<VerticalScrollViewViewModel>()
                VerticalScrollViewScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Page.VerticalRecyclerViewExample.route
            ) {
                val viewModel = hiltViewModel<VerticalRecyclerViewViewModel>()
                VerticalRecyclerViewScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Page.HorizontalScrollViewExample.route
            ) {
                val viewModel = hiltViewModel<HorizontalScrollViewViewModel>()
                HorizontalScrollViewScreen(
                    mainNavController = mainNavController,
                    viewModel = viewModel
                )
            }
        }

        // MOVIE
        navigation(
            route = Page.Movie.route,
            startDestination = Page.Movies.route
        ) {
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
}