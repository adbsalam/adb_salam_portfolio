package uk.adbsalam.portfolio.onboarding.data

enum class MediaType {
    IMAGE, LOTTI
}

enum class LayoutType {
    ONE, TWO, THREE, FOUR
}

data class OnBoardingPages(
    val list: List<OnBoardingPage>
) {
    data class OnBoardingPage(
        val title: String,
        val description: String,
        val mediaRes: String,
        val mediaType: MediaType,
        val layoutType: LayoutType
    )
}

val onBoardingPages = listOf(
    OnBoardingPages.OnBoardingPage(
        title = "Welcome",
        description = "Welcome to adb_salam Portfolio app, hope you found your way to this app easily. We will try our best to provide best possible experience using this app and surprise you with some cool components you can try out",
        mediaRes = "welcome",
        mediaType = MediaType.IMAGE,
        layoutType = LayoutType.ONE
    ),
    OnBoardingPages.OnBoardingPage(
        title = "About Us!",
        description = "This is Muhammad Abdul Salam's portfolio app, where you can find information about his personal and work life along with some samples/demos of Android components",
        mediaRes = "about_us",
        mediaType = MediaType.LOTTI,
        layoutType = LayoutType.TWO
    ),
    OnBoardingPages.OnBoardingPage(
        title = "Material 3 App",
        description = "This app is based on material 3 designs - App default theme and all components are M3",
        mediaRes = "material",
        mediaType = MediaType.IMAGE,
        layoutType = LayoutType.THREE
    ),
    OnBoardingPages.OnBoardingPage(
        title = "Custom Theming",
        description = "You can modify App theme based on how you would like, we have Light - Dark and for a secret theme that you will find. Hope you will like it",
        mediaRes = "theming",
        mediaType = MediaType.IMAGE,
        layoutType = LayoutType.FOUR
    ),
)