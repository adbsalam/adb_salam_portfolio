package uk.adbsalam.portfolio.navigation

const val HOME = "home"
const val INFO = "info"
const val VIDEOS = "videos"
const val REVIEWS = "reviews"

sealed class NavigationScreen(val name: String) {
    object OnHome : NavigationScreen(HOME)
    object OnVideos : NavigationScreen(INFO)
    object OnInfo : NavigationScreen(VIDEOS)
    object OnReviews : NavigationScreen(REVIEWS)
}