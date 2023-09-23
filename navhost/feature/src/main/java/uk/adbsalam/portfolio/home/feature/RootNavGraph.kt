package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

                    var manualReset = rememberSaveable { mutableStateOf(false) }

                    var duration = rememberSaveable { mutableStateOf(500f) }
                    var threshold = rememberSaveable { mutableStateOf(0.97f) }

                    var startOne = rememberSaveable { mutableStateOf(1f) }
                    var startTwo = rememberSaveable { mutableStateOf(1f) }

                    var startThree = rememberSaveable { mutableStateOf(1f) }
                    var startFour = rememberSaveable { mutableStateOf(1f) }
                    val scope = rememberCoroutineScope()


                    LaunchedEffect(
                        key1 = null,
                        block = {
                            delay(1000)
                            startOne.value = 0f

                            delay(150)
                            startTwo.value = 0f
                            startThree.value = 0f

                            delay(20)
                            startFour.value = 0f
                        }
                    )

                    Row() {
                        Cover(threshold = threshold, duration = duration, startHere = startOne, manualReset =  manualReset)
                        Spacer(modifier = Modifier.width(12.dp))
                        Cover(threshold = threshold, duration = duration, currentIndex = 1, startHere = startTwo, manualReset =  manualReset)
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Row() {
                        Cover(threshold = threshold, duration = duration, startHere = startThree, currentIndex = 2, manualReset =  manualReset)
                        Spacer(modifier = Modifier.width(12.dp))
                        Cover(threshold = threshold, duration = duration, currentIndex = 3, startHere = startFour, manualReset =  manualReset)
                    }

                    Column(Modifier.fillMaxWidth().padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Slider(
                            value = duration.value,
                            onValueChange = { duration.value = it },
                            valueRange = 0f..5000f
                        )
                        Text(text = duration.value.toString())

                        Spacer(modifier = Modifier.height(50.dp))

                        Slider(
                            value = threshold.value,
                            onValueChange = { threshold.value = it },
                            valueRange = 0.1f..0.99f
                        )
                        Text(text = threshold.value.toString())
                    }


                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            manualReset.value = true
                            startOne.value = 1f
                            startTwo.value = 1f
                            startThree.value = 1f
                            startFour.value = 1f

                        }) {
                            Text(text = "reset")
                        }

                        Button(onClick = {
                            manualReset.value = false
                            duration.value = 500f
                            threshold.value  = 0.97f
                            scope.launch {
                                delay(500)
                                startOne.value = 0f

                                delay(150)
                                startTwo.value = 0f
                                startThree.value = 0f
                                startFour.value = 0f
                            }
                        }) {
                            Text(text = "shimmer")
                        }
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