package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.ExitAlwaysFloatingToolbarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarExitDirection
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberFloatingToolbarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.xr.compose.material3.ExperimentalMaterial3XrApi
import androidx.xr.compose.material3.HorizontalFloatingToolbar
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.navigateToHome
import uk.adbsalam.portfolio.navigation.navigateToInfo
import uk.adbsalam.portfolio.navigation.navigateToReviews
import uk.adbsalam.portfolio.navigation.navigateToVideos
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.utils.Theme
import kotlin.math.abs

@OptIn(
    ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalMaterial3XrApi::class
)
@Composable
fun HomeNavHost(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    rootNavHostController: NavHostController,
) {
    val visibility = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(0) }
    val navController = rememberNavController()
    var lastScrollOffset by remember { mutableIntStateOf(0) }
    var scrollDirection by remember { mutableStateOf(ScrollDirection.NONE) }

    LaunchedEffect(key1 = null) {
        visibility.value = true
    }

    AnimatedVisibility(
        visible = visibility.value,
        enter = fadeIn(tween(500)),
    ) {
        Scaffold(
            containerColor = Color.Unspecified,
            floatingActionButton = {
                val slowerDecay = exponentialDecay<Float>(
                    frictionMultiplier = 1.5f // > 1.0 slows it down, < 1.0 speeds it up
                )
                HorizontalFloatingToolbar(
                    scrollBehavior = ExitAlwaysFloatingToolbarScrollBehavior(
                        state = rememberFloatingToolbarState(),
                        exitDirection = FloatingToolbarExitDirection.End, // or Down depending on your use case
                        snapAnimationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy, // adds bounce
                            stiffness = Spring.StiffnessLow                  // slower movement
                        ),
                        flingAnimationSpec = slowerDecay
                    ),
                    expanded = scrollDirection == ScrollDirection.NONE || scrollDirection == ScrollDirection.UP,
                    floatingActionButton = {
                        FloatingToolbarDefaults.VibrantFloatingActionButton(
                            onClick = {
                                scrollDirection =
                                    if (scrollDirection == ScrollDirection.DOWN) ScrollDirection.UP else ScrollDirection.DOWN
                            }
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(uk.adbsalam.portfolio.components.R.drawable.ic_logo_main),
                                contentDescription = null
                            )
                        }
                    },
                    content = {
                        IconButton(onClick = navController::navigateToHome) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = null,
                                tint = Color(0xFFFF9800)
                            )
                        }
                        IconButton(onClick = navController::navigateToInfo) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = null,
                                tint = Color(0xFF9C27B0)
                            )
                        }
                        IconButton(onClick = navController::navigateToVideos) {
                            Icon(
                                Icons.Filled.Movie,
                                contentDescription = null,
                                tint = Color(0xFF4CAF50)
                            )
                        }
                        IconButton(onClick = navController::navigateToReviews) {
                            Icon(
                                Icons.Filled.Forum,
                                contentDescription = null,
                                tint = Color.Cyan
                            )
                        }
                    },
                )
            },
            floatingActionButtonPosition = FabPosition.End,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding()),
            ) {
                HomeNavGraph(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor,
                    homeNavController = navController,
                    rootNavHostController = rootNavHostController,
                ) { scroll ->
                    val threshold = 100
                    val delta = scroll - lastScrollOffset
                    if (abs(delta) > threshold) {
                        scrollDirection = when {
                            delta > 0 -> ScrollDirection.DOWN
                            delta < 0 -> ScrollDirection.UP
                            else -> ScrollDirection.NONE
                        }
                        lastScrollOffset = scroll
                    }
                }
            }
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            NavigationScreen.OnHome.route() -> selected.value = 0
        }
    }
}

enum class ScrollDirection() {
    UP, DOWN, NONE
}
