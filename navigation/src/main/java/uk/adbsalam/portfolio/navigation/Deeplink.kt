package uk.adbsalam.portfolio.navigation

import androidx.navigation.NavHostController

const val deeplinkPatrolla = "/patrolla"
const val deeplinkSnapit = "/snapit"
const val deeplinkGallery = "/gallery"
const val deeplinkGesture = "/gesture"
const val deeplinkYoutube = "/youtube"

fun NavHostController.navigateDeepLink(deeplink: String) {
    val destination = when (deeplink) {
        deeplinkPatrolla -> NavigationScreen.OnPatrolla
        deeplinkSnapit -> NavigationScreen.OnSnapIt
        else -> NavigationScreen.OnGallery
    }
    this.toDestination(destination)
}