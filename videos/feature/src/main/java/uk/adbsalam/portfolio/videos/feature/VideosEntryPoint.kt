package uk.adbsalam.portfolio.videos.feature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.objects.VideoItems
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param viewModel view model to be used for this screen
 *
 * Perform functionality that might block preview here
 */
@Composable
fun Videos(viewModel: VideosViewModel = hiltViewModel()) {
    val uiState by viewModel.viewState.collectAsState()

    Videos(
        uiState = uiState,
        currentTheme = viewModel.currentTheme(),
        retry = viewModel::fetchVideos,
        onSetAutoplay = viewModel::setAutoPlay
    )
}

/**
 * @param uiState current VideosState Ui State to show here
 * @param currentTheme theme app is currently using
 */
@Composable
private fun Videos(
    uiState: VideosState,
    currentTheme: Theme,
    onSetAutoplay: (Boolean) -> Unit,
    retry: () -> Unit,
) {
    when (uiState) {
        VideosState.OnLoading ->
            LoadingLotti(
                modifier = Modifier.fillMaxSize(),
                msg = "Loading",
            )

        is VideosState.OnError -> {
            ErrorPage(
                msg = uiState.msg,
                retry = retry,
            )
        }

        is VideosState.OnVideos -> {
            VideosScreen(
                videos = uiState.videos,
                currentTheme = currentTheme,
                isAutoPlay = uiState.isAutoPlay,
                onSetAutoplay = onSetAutoplay
            )
        }
    }
}

@PreviewLight
@Composable
@SnapIt(gif = true, end = 1000L)
internal fun VideosPreviewLight() {
    Adb_Screen_Theme {
        Videos(
            uiState = VideosState.OnVideos(VideoItems.createMock(), false),
            currentTheme = Theme.LIGHT,
            onSetAutoplay = {},
            retry = { /*unused*/ },
        )
    }
}

@PreviewDark
@Composable
@SnapIt(gif = true, end = 1000L, isDark = true)
internal fun VideosPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Videos(
            uiState = VideosState.OnVideos(VideoItems.createMock(), false),
            currentTheme = Theme.DARK,
            onSetAutoplay = {},
            retry = { /*unused*/ },
        )
    }
}
