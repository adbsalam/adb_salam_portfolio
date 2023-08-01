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
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param onTheme action to perform on theme value selected
 * @param onDynamicColor action to perform on dynamic color value selected
 */
@Composable
fun Homepage(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.initHome()
    }

    Homepage(
        uiState = uiState,
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
internal fun Homepage(
    uiState: HomeScreenState,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {

    when (uiState) {
        HomeScreenState.OnLoading ->
            LoadingLotti(
                modifier = Modifier.fillMaxSize(),
                msg = "Loading"
            )

        is HomeScreenState.OnHome -> {
            var visibility by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = null) {
                visibility = true
            }
            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(500))
            ) {
                Home(
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
        Homepage(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
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
        Homepage(
            uiState = HomeScreenState.OnHome(HomeScreenItem.createMock()),
            onDynamicColor = { /* unused */ },
            onTheme = {/* unused */ },
        )
    }
}