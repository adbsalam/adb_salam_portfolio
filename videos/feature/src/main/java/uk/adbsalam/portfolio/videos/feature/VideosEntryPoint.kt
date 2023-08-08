package uk.adbsalam.portfolio.videos.feature

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.VideoData

@Composable
fun Videos(
    viewModel: VideosViewModel = hiltViewModel()
) {

    val uiState by viewModel.viewState.collectAsState()
    LaunchedEffect(key1 = null) { viewModel.loadVideos() }

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
        VideosState.OnLoading -> LoadingLotti()

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

@Preview
@Composable
fun VideosPreviewLight() {
    Adb_Theme {
        Videos(
            uiState = VideosState.OnVideos(VideoData.createMock()),
            currentTheme = Theme.LIGHT
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideosPreviewDark() {
    Adb_Theme(true) {
        Videos(
            uiState = VideosState.OnVideos(VideoData.createMock()),
            currentTheme = Theme.LIGHT
        )
    }
}


