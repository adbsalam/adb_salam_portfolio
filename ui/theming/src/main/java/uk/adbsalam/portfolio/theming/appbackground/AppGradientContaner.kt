package uk.adbsalam.portfolio.theming.appbackground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

/**
 * @param theme current theme to use for background
 * @param dynamicColor is dynamic theming enabled or false
 * @param content content to show on
 *
 * This is App's Primary theming container
 * All compose must be used with this to make sure gradient backgrounds are consistent
 */
@Composable
fun AppGradientContainer(
    theme: Theme,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    Adb_Theme(
        themeType = theme,
        dynamic = dynamicColor
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            GradientColumn(
                theme = theme
            ) {
                content()
            }
        }
    }
}