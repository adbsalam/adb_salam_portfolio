package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt


@Composable
fun Homepage(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = null) {
        viewModel.initHome()
        visible.value = true
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(tween(500))
    ) {
        Homepage(
            uiState = uiState,
            onDynamicColor = onDynamicColor,
            onTheme = onTheme
        )
    }
}

@Composable
internal fun Homepage(
    uiState: HomeScreenState,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {

    when (uiState) {
        HomeScreenState.OnLoading -> CircularProgressIndicator()
        HomeScreenState.OnHome ->
            Home(
                onDynamicColor = onDynamicColor,
                onTheme = onTheme
            )
    }

}

@Composable
@Preview
@SnapIt(
    name = "HomePage - Light Mode",
    isScreen = true
)
internal fun PreviewHomeLight() {
    Adb_Theme {
        Homepage(
            uiState = HomeScreenState.OnHome,
            onDynamicColor = {},
            onTheme = { },
        )
    }
}

@Composable
@Preview
@SnapIt(
    name = "HomePage - Dark Mode",
    isScreen = true
)
internal fun PreviewHomeDark() {
    Adb_Theme(
        isSystemDark = true
    ) {
        Homepage(
            uiState = HomeScreenState.OnHome,
            onDynamicColor = { /* unused */ },
            onTheme = {/* unused */ },
        )
    }
}