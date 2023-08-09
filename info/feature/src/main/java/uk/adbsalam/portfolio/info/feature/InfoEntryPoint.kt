package uk.adbsalam.portfolio.info.feature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param viewModel viewModel to use for this compose
 * This is entry point to Info Screen, all functionality that blocks Preview
 * Should be used here
 */
@Composable
fun Info(
    viewModel: InfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) { viewModel.initInfo() }

    Info(
        uiState = uiState,
        retry = viewModel::fetchInfoAndWork
    )
}

/**
 * @param uiState current UI State to show on screen
 * This is Info Screen to show according to InfoScreenState uiState passed
 */
@Composable
private fun Info(
    uiState: InfoScreenState,
    retry: () -> Unit
) {
    when (uiState) {
        InfoScreenState.OnLoading -> LoadingLotti(
            modifier = Modifier.fillMaxSize(),
            msg = "Loading"
        )

        is InfoScreenState.OnError -> {
            ErrorPage(
                msg = uiState.msg,
                retry = retry
            )
        }

        is InfoScreenState.OnInfo -> {
            AnimatedColumn {
                InfoScreen(
                    infographics = uiState.infographics,
                    workHistory = uiState.workHistory
                )
            }
        }
    }
}

@PreviewLight
@Composable
@SnapIt(gif = true, end = 1000L)
internal fun InfoScreenLightPreview() {
    Adb_Screen_Theme {
        Info(
            uiState = InfoScreenState.OnInfo(
                infographics = Infographics.createMock(),
                workHistory = WorkHistory.createMock()
            ),
            retry = {/*unused*/ }
        )
    }
}

@PreviewDark
@Composable
@SnapIt(gif = true, end = 1000L, isDark = true)
internal fun InfoScreenDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        Info(
            uiState = InfoScreenState.OnInfo(
                infographics = Infographics.createMock(),
                workHistory = WorkHistory.createMock()
            ),
            retry = {/*unused*/ }
        )
    }
}