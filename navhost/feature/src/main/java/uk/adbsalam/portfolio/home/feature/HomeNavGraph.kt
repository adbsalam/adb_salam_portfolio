package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import uk.adbsalam.portfolio.gallery.feature.Gallery
import uk.adbsalam.portfolio.info.feature.Info
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.navHostRoute
import uk.adbsalam.portfolio.reviews.feature.Reviews
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.feature.Videos

@Composable
fun HomeNavGraph(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    homeNavController: NavHostController,
    rootNavHostController: NavHostController,
    onScroll: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = homeNavController,
            startDestination = NavigationScreen.OnHome.navHostRoute(),
        ) {
            composeRoute(NavigationScreen.OnHome) {
                Home(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor,
                    navController = rootNavHostController,
                    onScroll = onScroll,
                )
            }

            composeRoute(NavigationScreen.OnInfo) {
                Info(onScroll = onScroll)
            }

            composeRoute(NavigationScreen.OnVideos) {
                Videos()
            }

            composeRoute(NavigationScreen.OnReviews) {
                Reviews(onScroll = onScroll)
            }

            composeRoute(NavigationScreen.OnGallery) {
                Gallery(navController = rootNavHostController)
            }
        }
    }
}
