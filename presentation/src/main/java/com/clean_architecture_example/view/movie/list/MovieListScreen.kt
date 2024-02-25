package com.clean_architecture_example.view.movie.list

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.clean_architecture_domain.entity.MovieEntity
import com.clean_architecture_example.R
import com.clean_architecture_example.R.drawable
import com.clean_architecture_example.navigation.Page
import com.clean_architecture_example.util.preview.PreviewContainer
import kotlinx.coroutines.flow.flowOf


@Composable
fun MovieListPage(
    mainNavController: NavHostController,
    viewModel: MovieListViewModel
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    MovieListScreen(mainNavController, movies)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    mainNavController: NavHostController,
    movies: LazyPagingItems<MovieEntity>,
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
                title = { Text(text = "영화 목록") },
                navigationIcon = {
                    IconButton(onClick = { mainNavController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(3),
            state = rememberLazyGridState()
        ) {
            items(movies.itemCount, span = { index ->
                GridItemSpan(1)
            }) { index ->
                val movie = movies[index]
                MovieItem(movie!!, ImageSize.getImageFixedSize()) { movieId ->
                    mainNavController.navigate(Page.MovieDetail.route + "/${movieId}")
                }
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: MovieEntity,
    imageSize: ImageSize,
    onMovieClick: (movieId: Int) -> Unit = {}
) {
    SubcomposeAsyncImage(
        model = movie.image,
        loading = { MovieItemPlaceholder() },
        error = { MovieItemPlaceholder() },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(3.dp)
            .size(imageSize.width, imageSize.height)
            .clickable { onMovieClick(movie.id) }
            .clip(RoundedCornerShape(2))
    )
}


private class ImageSize(
    val width: Dp,
    val height: Dp
) {
    companion object {
        @Composable
        fun getImageFixedSize(): ImageSize {
            val configuration = LocalConfiguration.current
            val imageWidth = configuration.screenWidthDp.dp / 3
            val imageHeight = imageWidth.times(1.3f)
            return ImageSize(imageWidth, imageHeight)
        }
    }
}


@Composable
fun MovieItemPlaceholder() {
    Image(
        painter = painterResource(id = R.drawable.bg_image),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_3, showSystemUi = true)
@Composable
private fun FeedScreenPreview() {
    val movies = flowOf(
        PagingData.from(
            listOf<MovieEntity>(
                MovieEntity(),
                MovieEntity()
            )
        )
    ).collectAsLazyPagingItems()
    val navController = rememberNavController()
    PreviewContainer {
        MovieListScreen(navController, movies)
    }
}