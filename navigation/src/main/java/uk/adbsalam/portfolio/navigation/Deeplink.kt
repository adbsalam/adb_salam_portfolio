package uk.adbsalam.portfolio.navigation

import androidx.navigation.NavHostController

const val deeplinkPatrolla = "/patrolla"
const val deeplinkSnapit = "/snapit"
const val deeplinkGallery = "/gallery"
const val deeplinkGesture = "/gesture"
const val deeplinkYoutube = "/youtube"
const val deeplinkShimmerCard = "/shimmer_card"

fun NavHostController.navigateDeepLink(deeplink: String) {
    val destination = when (deeplink) {
        deeplinkShimmerCard -> NavigationScreen.OnShimmerSample
        deeplinkGallery -> NavigationScreen.OnGallery
        else -> NavigationScreen.OnInProgress
    }
    this.toDestination(destination)
}