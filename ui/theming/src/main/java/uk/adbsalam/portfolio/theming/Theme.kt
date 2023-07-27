package uk.adbsalam.portfolio.theming

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uk.adbsalam.portfolio.utils.Theme

private val DarkColorScheme = darkColorScheme(
    primary = primary_dark,
    secondary = secondary_dark,
    tertiary = tertiary_dark,
    background = secondary_dark,
    onPrimary = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = primary_light,
    secondary = secondary_light,
    tertiary = tertiary_light,
    background = Color.White,
    secondaryContainer = secondary_container_light
)

/**
 *
 */
@Composable
fun Adb_Theme(
    isSystemDark: Boolean = isSystemInDarkTheme(),
    themeType: Theme = Theme.SYSTEM,
    dynamic: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val sch: ColorScheme = if (dynamic) {
        getDynamicScheme(isSystemDark, themeType, context)
    } else {
        getNonDynamicTheme(isSystemDark, themeType)
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    val view = LocalView.current

    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    MaterialTheme(
        colorScheme = sch,
        typography = MaterialTheme.typography,
        content = content
    )
}

/**
 *
 */
fun getDynamicScheme(
    isSystemDark: Boolean,
    selectedType: Theme,
    context: Context
): ColorScheme {
    return when (selectedType) {
        Theme.LIGHT -> dynamicLightColorScheme(context)
        Theme.DARK -> dynamicDarkColorScheme(context)
        Theme.SYSTEM ->
            if (isSystemDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
}

/**
 *
 */
fun getNonDynamicTheme(
    isSystemDark: Boolean,
    selectedType: Theme
): ColorScheme {
    return when (selectedType) {
        Theme.SYSTEM -> if (isSystemDark) DarkColorScheme else LightColorScheme
        Theme.LIGHT -> LightColorScheme
        Theme.DARK -> DarkColorScheme
    }
}