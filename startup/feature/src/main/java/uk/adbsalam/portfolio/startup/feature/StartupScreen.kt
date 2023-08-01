package uk.adbsalam.portfolio.startup.feature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.home.feature.HomeScreenNavHost
import uk.adbsalam.portfolio.startup.feature.components.LoadingScreen
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Startup Screen Main Composable
 *
 * This is separated from main screen view since
 * some of the components cannot be used as preview
 * This will allow previews for this screen
 */
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

/**
 * @param uiState current ui state to show on screen
 * @param theme current theme of the app to use as pre-selected
 * @param dynamicColor current dynamic colors value to use as pre-selected
 * @param onTheme action to perform on new theme selected
 * @param onDynamicColor action to perform on new dynamic color value selected
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                StartupState.OnStart -> HomeScreenNavHost(
                    theme = theme,
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