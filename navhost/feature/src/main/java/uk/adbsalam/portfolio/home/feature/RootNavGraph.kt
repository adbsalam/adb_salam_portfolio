package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import uk.adbsalam.portfolio.info.feature.Info
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.reviews.feature.Reviews
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.feature.Videos


@Composable
fun RootNavGraph(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.OnHome.route(),
        ) {
            composeRoute(NavigationScreen.OnHome) {
                Home(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor
                )
            }

            composeRoute(NavigationScreen.OnInfo) {
                Info()
            }

            composeRoute(NavigationScreen.OnVideos) {
                Videos()
            }

            composeRoute(NavigationScreen.OnReviews) {
                Reviews()
            }
        }
    }
}

