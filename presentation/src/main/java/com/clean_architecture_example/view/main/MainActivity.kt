package com.clean_architecture_example.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.clean_architecture_example.ui.theme.CleanarchitectureexampleTheme
import com.clean_architecture_example.view.dialog.custom_loading_dialog.GlobalLoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            CleanarchitectureexampleTheme {
                Box {
                    MainGraph(mainNavController = navController)
                    GlobalLoadingScreen()
                }

            }
        }
    }
}

