package uk.adbsalam.portfolio.navigation

const val HOME_NAV = "home_nav"
const val HOME = "home"
const val INFO = "info"
const val VIDEOS = "videos"
const val REVIEWS = "reviews"
const val PATROLLA_DEEPLINK = "patrolla"
const val SNAPIT_DEEPLINK = "snapit"
const val GALLERY_DEEPLINK = "gallery"
const val FULL_SCREEN_GALLERY = "fullsreen_gallery"
const val GESTURE_DEEPLINK = "gesture"
const val YOUTUBE_DEEPLINK = "youtube"

sealed class NavigationScreen(val name: String) {
    object OnHomeNav : NavigationScreen(HOME_NAV)
    object OnHome : NavigationScreen(HOME)
    object OnVideos : NavigationScreen(INFO)
    object OnInfo : NavigationScreen(VIDEOS)
    object OnReviews : NavigationScreen(REVIEWS)
    object OnPatrolla : NavigationScreen(PATROLLA_DEEPLINK)
    object OnSnapIt : NavigationScreen(SNAPIT_DEEPLINK)
    object OnGallery : NavigationScreen(GALLERY_DEEPLINK)
    object OnFullScreenGallery : NavigationScreen(FULL_SCREEN_GALLERY)
}