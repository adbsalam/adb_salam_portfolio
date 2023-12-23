package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.blog.feature.BlogScreen
import uk.adbsalam.portfolio.gallery.feature.Gallery
import uk.adbsalam.portfolio.gallery.feature.fullscreen.FullscreenGallery
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.samples.feature.InProgressPage
import uk.adbsalam.portfolio.samples.feature.ShimmerCardSample
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun RootNavGraph(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
) {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.OnHome.route(),
        ) {
            composeRoute(NavigationScreen.OnHome) {
                HomeNavHost(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor,
                    rootNavHostController = navController
                )
            }

            composeRoute(NavigationScreen.OnInProgress) {
                InProgressPage()
            }

            composeRoute(NavigationScreen.OnBlogScreen) {
                BlogScreen(navController = navController)
            }

            composeRoute(NavigationScreen.OnShimmerSample) {
                ShimmerCardSample()
            }

            composeRoute(NavigationScreen.OnGallery) {
                Gallery(navController = navController)
            }

            composeRoute(NavigationScreen.OnFullScreenGallery) {
                FullscreenGallery(navController = navController)
            }
        }
    }
}