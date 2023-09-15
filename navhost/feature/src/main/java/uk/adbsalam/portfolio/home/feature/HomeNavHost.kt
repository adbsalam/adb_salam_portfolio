package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun HomeNavHost(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    rootNavHostController: NavHostController
) {
    val visibility = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    LaunchedEffect(key1 = null) {
        visibility.value = true
    }

    AnimatedVisibility(
        visible = visibility.value,
        enter = fadeIn(tween(500))
    ) {
        Scaffold(
            containerColor = Color.Unspecified,
            bottomBar = {
                RootNavBar(
                    selected = selected,
                    navController = navController
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                HomeNavGraph(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor,
                    homeNavController = navController,
                    rootNavHostController = rootNavHostController
                )
            }
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            NavigationScreen.OnHome.route() -> selected.value = 0
        }
    }
}