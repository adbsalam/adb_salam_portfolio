package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
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

                    var duration = rememberSaveable { mutableStateOf(500f) }
                    var threshold = rememberSaveable { mutableStateOf(0.97f) }

                    var startOne = rememberSaveable { mutableStateOf(1f) }
                    var startTwo = rememberSaveable { mutableStateOf(1f) }

                    LaunchedEffect(
                        key1 = null,
                        block = {
                            delay(1000)
                            startOne.value = 0f

                            delay(150)
                            startTwo.value = 0f
                        }
                    )

                    Row() {
                        Cover(threshold = threshold, duration = duration, startHere = startOne)
                        Spacer(modifier = Modifier.width(12.dp))
                        Cover(threshold = threshold, duration = duration, currentIndex = 1, startHere = startTwo)
                    }

                    Slider(
                        value = duration.value,
                        onValueChange = { duration.value = it },
                        valueRange = 0f..5000f
                    )
                    Text(text = duration.toString())

                    Spacer(modifier = Modifier.height(50.dp))

                    Slider(
                        value = threshold.value,
                        onValueChange = { threshold.value = it },
                        valueRange = 0.1f..0.99f
                    )
                    Text(text = threshold.toString())
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