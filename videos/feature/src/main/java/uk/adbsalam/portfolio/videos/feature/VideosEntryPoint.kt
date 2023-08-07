package uk.adbsalam.portfolio.videos.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.utils.Theme


@Composable
fun Videos(
    viewModel: VideosViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.loadVideos()
    }

    Videos(
        uiState = uiState,
        currentTheme = viewModel.currentTheme()
    )
}

@Composable
private fun Videos(
    uiState: VideosState,
    currentTheme: Theme
) {

    when (uiState) {
        VideosState.OnLoading ->
            LoadingLotti()

        is VideosState.OnVideos -> {

            var animate by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = null) {
                animate = true
            }

            AnimatedVisibility(
                visible = animate,
                enter = fadeIn(tween(500))
            ) {
                VideosScreen(
                    videos = uiState.videos,
                    currentTheme = currentTheme
                )
            }
        }
    }
}


