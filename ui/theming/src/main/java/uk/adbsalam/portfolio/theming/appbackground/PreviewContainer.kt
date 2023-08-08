package uk.adbsalam.portfolio.theming.appbackground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

/**
 * Preview Container to make use of in Previews
 * Since Previews would either be dark or light
 * User this for screen previews
 */
@Composable
fun Adb_Screen_Theme(
    isDark: Boolean = false,
    content: @Composable () -> Unit
) {
    Adb_Theme(
        isSystemDark = isDark
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            GradientColumn(theme = if (isDark) Theme.DARK else Theme.LIGHT) {
                content()
            }
        }
    }
}