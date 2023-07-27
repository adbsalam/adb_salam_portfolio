package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun HomeScreenNavHost(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
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