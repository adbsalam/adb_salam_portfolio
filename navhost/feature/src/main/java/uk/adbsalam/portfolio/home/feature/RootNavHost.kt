package uk.adbsalam.portfolio.home.feature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.home.feature.components.GradientColumn
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.utils.Theme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreenNavHost(
    theme: Theme,
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    GradientColumn(theme = theme) {
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.OnHome.route(),
        ) {

            composeRoute(NavigationScreen.OnHome) {
                NavBarScreen(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor
                )
            }
        }
    }
}

