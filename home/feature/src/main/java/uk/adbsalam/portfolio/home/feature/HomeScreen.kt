package uk.adbsalam.portfolio.home.feature

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.settings.feature.SettingsDialog
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun HomeScreen(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.initHome()
    }

    HomeScreen(
        isDynamic = viewModel.isDynamicColors(),
        theme = viewModel.theme(),
        uiState = uiState,
        onDynamicColor = onDynamicColor,
        onTheme = onTheme
    )
}

@Composable
private fun HomeScreen(
    isDynamic: Boolean,
    theme: Theme,
    uiState: HomeScreenState,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {
    val settings = remember { mutableStateOf(false) }

    when (uiState) {
        HomeScreenState.OnLoading -> {}
        HomeScreenState.OnHome ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.3f)
                        .background(MaterialTheme.colorScheme.primary)
                ) {}
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .statusBarsPadding()
                        .padding(10.dp)
                        .clickable {
                            settings.value = true
                        }
                )
            }
    }

    if (settings.value) {
        SettingsDialog(
            isDynamic = isDynamic,
            theme = theme,
            onDynamicColor = onDynamicColor,
            onTheme = onTheme,
            onDismiss = { settings.value = false }
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun HomeScreenOnHomeLightTheme() {
    Adb_Theme {
        HomeScreen(
            isDynamic = false,
            theme = Theme.LIGHT,
            uiState = HomeScreenState.OnHome,
            onDynamicColor = { /** unused **/ },
            onTheme = { /** unused **/ }
        )
    }
}



