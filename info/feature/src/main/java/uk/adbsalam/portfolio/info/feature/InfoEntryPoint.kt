package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight

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

    LaunchedEffect(key1 = null) {
        viewModel.initInfo()
    }

    Info(
        uiState = uiState
    )
}

/**
 * @param uiState current UI State to show on screen
 * This is Info Screen to show according to InfoScreenState uiState passed
 */
@Composable
private fun Info(
    uiState: InfoScreenState
) {
    when (uiState) {
        InfoScreenState.OnLoading -> LoadingLotti(
            modifier = Modifier.fillMaxSize(),
            msg = "Loading"
        )

        InfoScreenState.OnInfo -> {
            AnimatedColumn {
                InfoScreen()
            }
        }
    }
}

@PreviewLight
@Composable
internal fun InfoScreenLightPreview() {
    Adb_Theme {
        Info(
            uiState = InfoScreenState.OnInfo
        )
    }
}

@PreviewDark
@Composable
internal fun InfoScreenDarkPreview() {
    Adb_Theme(true) {
        Info(
            uiState = InfoScreenState.OnInfo
        )
    }
}