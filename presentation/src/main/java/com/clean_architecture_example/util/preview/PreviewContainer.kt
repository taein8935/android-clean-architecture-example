package com.clean_architecture_example.util.preview

import androidx.compose.runtime.Composable
import com.clean_architecture_example.ui.theme.CleanarchitectureexampleTheme

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    CleanarchitectureexampleTheme {
        content()
    }
}