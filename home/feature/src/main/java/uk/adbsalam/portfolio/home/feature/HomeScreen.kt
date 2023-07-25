package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.adbsalam.portfolio.home.feature.navbar.BottomNavItem
import uk.adbsalam.portfolio.home.feature.navbar.HomeBottomNavBar
import uk.adbsalam.portfolio.settings.feature.SettingsDialog
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun HomeScreen(
    theme: Theme,
    isDynamicColor: Boolean,
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val settings = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    settings.value = true
                }
        )

        if (settings.value) {
            SettingsDialog(
                isDynamic = isDynamicColor,
                theme = theme,
                onDynamicColor = onDynamicColor,
                onTheme = onTheme,
                onDismiss = { settings.value = false }
            )
        }

        HomeBottomNavBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}