package uk.adbsalam.portfolio.home.feature

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
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param onTheme action to perform on theme value selected
 * @param onDynamicColor action to perform on dynamic color value selected
 */
@Composable
fun Home(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.initHome()
    }

    Home(
        uiState = uiState,
        retry = viewModel::loadHomeItems,
        onDynamicColor = onDynamicColor,
        onTheme = onTheme
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
            var visibility by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = null) {
                visibility = true
            }
            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(500))
            ) {
                HomeScreen(
                    items = uiState.homeItems,
                    onDynamicColor = onDynamicColor,
                    onTheme = onTheme
                )
            }
        }

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
        Home(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
            retry = { /* unused */ },
            onDynamicColor = {},
            onTheme = { },
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@SnapIt(
    name = "HomePage - Dark Mode",
    isScreen = true
)
internal fun PreviewHomeDark() {
    Adb_Theme(
        isSystemDark = true
    ) {
        Home(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
            retry = { /* unused */ },
            onDynamicColor = { /* unused */ },
            onTheme = {/* unused */ },
        )
    }
}