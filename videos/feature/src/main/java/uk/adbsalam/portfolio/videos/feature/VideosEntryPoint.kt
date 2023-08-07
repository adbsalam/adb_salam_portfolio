package uk.adbsalam.portfolio.videos.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
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
            AnimatedColumn {
                VideosScreen(
                    videos = uiState.videos,
                    currentTheme = currentTheme
                )
            }
        }
    }
}


