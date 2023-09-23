package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.gallery.feature.Gallery
import uk.adbsalam.portfolio.gallery.feature.fullscreen.FullscreenGallery
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
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

            composeRoute(NavigationScreen.OnPatrolla) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row() {
                        Cover()
                        Spacer(modifier = Modifier.width(12.dp))
                        Cover()
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row() {
                        Cover()
                        Spacer(modifier = Modifier.width(12.dp))
                        Cover()
                    }
                }
            }

            composeRoute(NavigationScreen.OnSnapIt) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Snapit Page")
                }
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