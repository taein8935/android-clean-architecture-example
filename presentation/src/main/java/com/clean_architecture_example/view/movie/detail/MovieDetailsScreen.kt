package com.clean_architecture_example.view.movie.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MovieDetailScreen(
    mainNavController: NavHostController,
    viewModel: MovieDetailViewModel
) {

    Column {
        Button(onClick = {
            mainNavController.popBackStack()
        }) {
            Text(text = "Back To List Screen")
        }
        Button(onClick = {
        }) {
            Text(text = "Click me")
        }
    }

}