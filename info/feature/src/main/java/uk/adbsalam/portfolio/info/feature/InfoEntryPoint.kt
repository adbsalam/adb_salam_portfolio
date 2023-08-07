package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.Adb_Theme

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
            var visibility by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = null) {
                visibility = true
            }

            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(500))
            ) {
                InfoScreen()
            }
        }
    }
}

@Preview
@Composable
fun InfoScreenLightPreview() {
    Adb_Theme {
        Info(
            uiState = InfoScreenState.OnInfo
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoScreenDarkPreview() {
    Adb_Theme(true) {
        Info(
            uiState = InfoScreenState.OnInfo
        )
    }
}