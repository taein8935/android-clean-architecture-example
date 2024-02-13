package com.clean_architecture_example.view.movie.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_example.R.drawable
import com.clean_architecture_example.util.preview.PreviewContainer


@Composable
fun MovieDetailPage(
    mainNavController: NavHostController,
    viewModel: MovieDetailViewModel
) {
    val state = viewModel.movieData.collectAsState().value
    MovieDetailsScreen(state, mainNavController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    state: MovieEntity,
    appNavController: NavHostController
) {
    val favoriteIcon = drawable.ic_favorite_fill_white // drawable.ic_favorite_border_white

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = favoriteIcon),
                    contentDescription = null,
                    Modifier.size(24.dp),
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Overview") },
                navigationIcon = {
                    IconButton(onClick = { appNavController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize(1f)
                .padding(paddingValues)
        ) {
            SubcomposeAsyncImage(
                model = state.backgroundUrl,
                loading = { MovieItemPlaceholder() },
                error = { MovieItemPlaceholder() },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth(1f)
            )

            Text(
                text = state.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .fillMaxWidth(1f),
            )

            Text(
                text = state.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .fillMaxWidth(1f),
            )
        }
    }
}

@Composable
private fun MovieItemPlaceholder() {
    Image(
        painter = painterResource(id = drawable.bg_image),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MovieDetailsScreenPreview() {
    PreviewContainer {
        MovieDetailsScreen(
            MovieEntity(
                id = 1,
                title = "Avatar",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.",
                category = "Action",
                image = "https://i.stack.imgur.com/lDFzt.jpg",
                backgroundUrl = "https://i.stack.imgur.com/lDFzt.jpg"
            ),
            rememberNavController()
        )
    }
}