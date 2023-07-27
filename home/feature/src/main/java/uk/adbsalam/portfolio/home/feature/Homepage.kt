package uk.adbsalam.portfolio.home.feature

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme


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


@Composable
private fun Homepage(
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
fun PreviewHomeLight() {
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
fun PreviewHomeDark() {
    Adb_Theme(
        isSystemDark = true
    ) {
        Homepage(
            uiState = HomeScreenState.OnHome,
            onDynamicColor = {},
            onTheme = { },
        )
    }
}