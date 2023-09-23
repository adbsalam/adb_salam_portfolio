package uk.adbsalam.portfolio.theming

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uk.adbsalam.portfolio.utils.Theme

private val DarkColorScheme = darkColorScheme(
    primary = primary_dark,
    secondary = secondary_dark,
    tertiary = tertiary_dark,
    background = dark_background,
    onPrimary = Color.White,
    secondaryContainer = primary_dark,
    inverseSurface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = primary_light,
    secondary = secondary_light,
    tertiary = tertiary_light,
    background = Color.White,
    secondaryContainer = secondary_container_light,
    inverseSurface = Color.White
)

private val Christmas = lightColorScheme(
    primary = Color.Black,
    secondary = Color.White,
    tertiary = Color.White,
    background = Color(0xFFF08A8A),
    secondaryContainer = Color.White,
    inverseSurface = Color.Red
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
        getNonDynamicTheme(isSystemInDarkTheme(), themeType)
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    val view = LocalView.current

    if (!view.isInEditMode) {
        try { // paparazzi throws exception
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    MaterialTheme(
        colorScheme = sch,
        content = content,
        typography = Typography(
            displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = appFont),
            displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = appFont),
            displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = appFont),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = appFont),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = appFont),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = appFont),
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                fontFamily = appFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                fontFamily = appFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            titleSmall = MaterialTheme.typography.titleSmall.copy(
                fontFamily = appFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            ),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = appFont),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = appFont),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = appFont),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = appFont),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = appFont),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = appFont)
        )
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
        Theme.CHRISTMAS -> Christmas
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
        Theme.CHRISTMAS -> Christmas
        Theme.LIGHT -> LightColorScheme
        Theme.DARK -> DarkColorScheme
    }
}