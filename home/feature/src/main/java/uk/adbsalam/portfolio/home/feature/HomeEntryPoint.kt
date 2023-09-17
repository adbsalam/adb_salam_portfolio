package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.navigation.navigateDeepLink
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param onTheme action to perform on theme value selected
 * @param onDynamicColor action to perform on dynamic color value selected
 * @param viewModel view model to be used for this screen
 *
 * Perform functionality that might block preview here
 */
@Composable
fun Home(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    Home(
        uiState = uiState,
        retry = viewModel::loadHomeItems,
        onDynamicColor = onDynamicColor,
        onTheme = onTheme,
        navigateDeeplink = navController::navigateDeepLink
    )
}

/**
 * @param uiState current UI State to show on screen
 * @param onTheme action to perform on theme value selected
 * @param onDynamicColor action to perform on dynamic color value selected
 */
@Composable
internal fun Home(
    uiState: HomeScreenState,
    retry: () -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    navigateDeeplink: (String) -> Unit
) {

    when (uiState) {
        HomeScreenState.OnLoading ->
            LoadingLotti(
                modifier = Modifier.fillMaxSize(),
                msg = "Loading"
            )

        is HomeScreenState.OnError -> {
            ErrorPage(
                msg = uiState.errorMessage,
                retry = retry
            )
        }

        is HomeScreenState.OnHome -> {
            HomeScreen(
                items = uiState.homeItems,
                onDynamicColor = onDynamicColor,
                onTheme = onTheme,
                navigateDeeplink = navigateDeeplink
            )
        }
    }
}

@PreviewLight
@Composable
@SnapIt(gif = true, end = 1000L)
internal fun HomePreviewLight() {
    Adb_Screen_Theme {
        Home(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
            retry = { /* unused */ },
            onDynamicColor = { /* unused */ },
            onTheme = { /* unused */ },
            navigateDeeplink = { /* unused */ }
        )
    }
}

@PreviewDark
@Composable
@SnapIt(gif = true, isDark = true, end = 1000L)
internal fun HomePreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Home(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
            retry = { /* unused */ },
            onDynamicColor = { /* unused */ },
            onTheme = { /* unused */ },
            navigateDeeplink = { /* unused */ }
        )
    }
}