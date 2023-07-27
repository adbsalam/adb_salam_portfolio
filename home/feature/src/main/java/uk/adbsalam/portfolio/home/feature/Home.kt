package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.SettingsIcon
import uk.adbsalam.portfolio.home.feature.components.InfoCard
import uk.adbsalam.portfolio.home.feature.components.Profile
import uk.adbsalam.portfolio.settings.feature.SettingsDialog
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

@Composable
internal fun Home(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {
    val settings = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val headerHeight = LocalConfiguration.current.screenHeightDp / 3

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight.dp)
                .statusBarsPadding()
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 5.5f)
                    translationY = 0.8f * scrollState.value
                },
        ) {
            Profile(
                modifier = Modifier.align(Alignment.Center)
            )

            SettingsIcon(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = { settings.value = true }
            )
        }


        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            for (i in 0..20) {
                InfoCard()
            }
        }
    }


    if (settings.value) {
        SettingsDialog(
            onDynamicColor = onDynamicColor,
            onTheme = onTheme,
            onDismiss = { settings.value = false }
        )
    }
}

@Preview
@Composable
internal fun HomeLightPreview() {
    Adb_Theme {
        Home(
            onDynamicColor = {/* unused */ },
            onTheme = {/* unused */ }
        )
    }
}

@Preview
@Composable
internal fun HomeDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        Home(
            onDynamicColor = {/* unused */ },
            onTheme = {/* unused */ }
        )
    }
}