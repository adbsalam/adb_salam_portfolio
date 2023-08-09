package uk.adbsalam.portfolio.videos.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.objects.VideoItems

/**
 * @param viewModel view model to be used for this screen
 *
 * Perform functionality that might block preview here
 */
@Composable
fun Videos(
    viewModel: VideosViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) { viewModel.loadVideos() }

    Videos(
        uiState = uiState,
        currentTheme = viewModel.currentTheme(),
        retry = viewModel::fetchVideos
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
    retry: () -> Unit,
) {
    when (uiState) {
        VideosState.OnLoading -> LoadingLotti()

        is VideosState.OnError -> {
            ErrorPage(
                msg = uiState.msg,
                retry = retry
            )
        }

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

@PreviewLight
@Composable
internal fun VideosPreviewLight() {
    Adb_Screen_Theme {
        Videos(
            uiState = VideosState.OnVideos(VideoItems.createMock()),
            currentTheme = Theme.LIGHT,
            retry = {/*unused*/ }
        )
    }
}

@PreviewDark
@Composable
internal fun VideosPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Videos(
            uiState = VideosState.OnVideos(VideoItems.createMock()),
            currentTheme = Theme.DARK,
            retry = {/*unused*/ }
        )
    }
}


