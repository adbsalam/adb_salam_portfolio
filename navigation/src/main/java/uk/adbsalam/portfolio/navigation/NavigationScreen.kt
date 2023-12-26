package uk.adbsalam.portfolio.navigation

const val HOME_NAV = "home_nav"
const val HOME = "home"
const val INFO = "info"
const val VIDEOS = "videos"
const val REVIEWS = "reviews"
const val PATROLLA_DEEPLINK = "patrolla"
const val GALLERY_DEEPLINK = "gallery"
const val FULL_SCREEN_GALLERY = "fullsreen_gallery"
const val SHIMMER_SAMPLE_DEEPLINK = "shimmer_sample"
const val GESTURE_DEEPLINK = "gesture"
const val YOUTUBE_DEEPLINK = "youtube"
const val IN_PROGRESS_DEEPLINK = "in_progress"
const val BLOG_SCREEN_DEEPLINK = "blog_screen"

sealed class NavigationScreen(val name: String) {
    object OnHomeNav : NavigationScreen(HOME_NAV)
    object OnHome : NavigationScreen(HOME)
    object OnVideos : NavigationScreen(INFO)
    object OnInfo : NavigationScreen(VIDEOS)
    object OnReviews : NavigationScreen(REVIEWS)
    object OnInProgress : NavigationScreen(IN_PROGRESS_DEEPLINK)
    object OnShimmerSample : NavigationScreen(SHIMMER_SAMPLE_DEEPLINK)
    object OnGallery : NavigationScreen(GALLERY_DEEPLINK)
    object OnFullScreenGallery : NavigationScreen(FULL_SCREEN_GALLERY)
    object OnBlogScreen : NavigationScreen(BLOG_SCREEN_DEEPLINK)
}