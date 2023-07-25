package uk.adbsalam.portfolio.startup.feature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.home.feature.HomeScreen
import uk.adbsalam.portfolio.startup.feature.components.LoadingScreen
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
internal fun StartupScreen() {
    val mainViewModel: StartupViewModel = hiltViewModel()
    val theme by mainViewModel.currentTheme.collectAsState()
    val dynamic by mainViewModel.dynamic.collectAsState()
    val uiState by mainViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = null) {
        mainViewModel.initApp()
    }

    StartupScreen(
        uiState = uiState,
        theme = theme,
        dynamicColor = dynamic,
        onTheme = mainViewModel::onThemeChange,
        onDynamicColor = mainViewModel::onDynamic
    )
}

@Composable
private fun StartupScreen(
    uiState: StartupState,
    theme: Theme,
    dynamicColor: Boolean,
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    Adb_Theme(
        themeType = theme,
        dynamic = dynamicColor
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                StartupState.OnLoading -> LoadingScreen()
                StartupState.OnStart -> HomeScreen(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor,
                )
            }
        }
    }
}

@Composable
@Preview
@SnapIt("StartupScreen - when in light mode - should render correctly")
internal fun StartupScreenLoadingLightTheme() {
    StartupScreen(
        uiState = StartupState.OnLoading,
        theme = Theme.LIGHT,
        dynamicColor = false,
        onTheme = { /** unused **/ },
        onDynamicColor = { /** unused **/ },
    )
}

@Composable
@Preview
@SnapIt("StartupScreen - when in dark mode - should render correctly")
internal fun StartupScreenLoadingDarkTheme() {
    StartupScreen(
        uiState = StartupState.OnLoading,
        theme = Theme.DARK,
        dynamicColor = false,
        onTheme = { /** unused **/ },
        onDynamicColor = { /** unused **/ },
    )
}