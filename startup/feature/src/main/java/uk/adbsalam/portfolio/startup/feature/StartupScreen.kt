package uk.adbsalam.portfolio.startup.feature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.home.feature.RootNavGraph
import uk.adbsalam.portfolio.startup.feature.components.LoadingScreen
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.AppGradientContainer
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Startup Screen Main Composable
 *
 * This is separated from main screen view since
 * some of the components cannot be used as preview
 * This will allow previews for this screen
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
@Composable
private fun StartupScreen(
    uiState: StartupState,
    theme: Theme,
    dynamicColor: Boolean,
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    AppGradientContainer(
        theme = theme,
        dynamicColor = dynamicColor
    ) {
        when (uiState) {
            StartupState.OnLoading -> LoadingScreen()
            StartupState.OnStart -> RootNavGraph(
                onTheme = onTheme,
                onDynamicColor = onDynamicColor,
            )
        }
    }
}

@Composable
@PreviewLight
@SnapIt(isScreen = true)
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
@PreviewDark
@SnapIt(isScreen = true, isDark = true)
internal fun StartupScreenLoadingDarkTheme() {
    StartupScreen(
        uiState = StartupState.OnLoading,
        theme = Theme.DARK,
        dynamicColor = false,
        onTheme = { /** unused **/ },
        onDynamicColor = { /** unused **/ },
    )
}