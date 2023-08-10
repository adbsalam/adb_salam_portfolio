package uk.adbsalam.portfolio.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

private fun NavController.rootNavOptions() = NavOptions
    .Builder()
    .setPopUpTo(
        this.graph.startDestinationId,
        inclusive = false,
        saveState = true
    )
    .setLaunchSingleTop(true)
    .setRestoreState(true)
    .build()

fun NavController.navigateHomeNavHost() {
    this.navigate(NavigationScreen.OnHomeNav.route(), this.rootNavOptions())
}

fun NavController.navigateToHome() {
    this.navigate(NavigationScreen.OnHome.route(), this.rootNavOptions())
}

fun NavController.navigateToInfo() {
    this.navigate(NavigationScreen.OnInfo.route(), this.rootNavOptions())
}

fun NavController.navigateToVideos() {
    this.navigate(NavigationScreen.OnVideos.route(), this.rootNavOptions())
}

fun NavController.navigateToReviews() {
    this.navigate(NavigationScreen.OnReviews.route(), this.rootNavOptions())
}