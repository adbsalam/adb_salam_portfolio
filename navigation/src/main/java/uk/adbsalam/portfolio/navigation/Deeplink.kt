package uk.adbsalam.portfolio.navigation

import androidx.navigation.NavHostController

const val deeplinkGallery = "/gallery"
const val deeplinkGesture = "/gesture"
const val deeplinkYoutube = "/youtube"
const val deeplinkShimmerCard = "/shimmer_card"

fun NavHostController.navigateDeepLink(deeplink: String) {
    if (deeplink.isBlogLink()) {
        val blogQuery = deeplink.replace("blog/", "")
        this.navigateToBlogScreen(BlogScreenArgs(query = blogQuery))
    } else {
        val destination = when (deeplink) {
            deeplinkShimmerCard -> NavigationScreen.OnShimmerSample
            deeplinkGallery -> NavigationScreen.OnGallery
            else -> NavigationScreen.OnInProgress
        }
        this.toDestination(destination)
    }
}

private fun String.isBlogLink() = this.contains("blog/")