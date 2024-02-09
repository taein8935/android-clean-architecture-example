package com.clean_architecture_example.view.movie.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MovieDetailScreen(
    mainNavController: NavHostController,
    viewModel: MovieDetailViewModel
) {
    Column {
        Text(text = viewModel.movie.value.toString())
    }
}