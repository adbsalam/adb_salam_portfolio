package uk.adbsalam.portfolio.home.feature.utils

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import uk.adbsalam.portfolio.components.R

internal data class HomeScreenItem(
    val tags: List<String>,
    val title: String,
    val type: HomeItemType,
    val res: String,
    val body: String,
    val deeplink: String
) {

    companion object {
        fun createMock(): List<HomeScreenItem> {
            return listOf(
                HomeScreenItem(
                    tags = listOf("Android", "Test"),
                    title = "Test Title",
                    type = HomeItemType.LOTTI_CARD,
                    res = "patrolla",
                    body = "This is body text",
                    deeplink = "/patrolla"
                ),

                HomeScreenItem(
                    tags = listOf("Android", "Test"),
                    title = "Test Title",
                    type = HomeItemType.IMAGE_CARD,
                    res = "snapit",
                    body = "This is body text",
                    deeplink = "/patrolla"
                ),
                HomeScreenItem(
                    tags = listOf("Android", "Test"),
                    title = "Test Title",
                    type = HomeItemType.IMAGE_CARD,
                    res = "walkie",
                    body = "This is body text",
                    deeplink = "/walkie"
                )
            )
        }
    }
}

internal val walkieCard =  HomeScreenItem(
    tags = listOf("Android", "Nearby Share", "Comms"),
    title = "Walkie Talkie",
    type = HomeItemType.IMAGE_CARD,
    res = "walkie",
    body = "Checkout this sample that converts your device into walkie talkie. You would need two devices, turn on this feature on both and enjoy talking to your buddy over a  wireless communication",
    deeplink = "/walkie"
)

internal enum class HomeItemType(val type: String) {
    IMAGE_CARD("image"),
    LOTTI_CARD("lotti"),
    LOTTI_SINGLE("single_anim_lotti")
}

@RawRes
internal fun getRawRes(res: String): Int {
    return when (res) {
        "patrol" -> R.raw.lotti_patrolla
        "gesture" -> R.raw.lotti_tv
        "gallery" -> R.raw.lotti_camera
        else -> 0
    }
}

@DrawableRes
internal fun getDrawableRes(res: String): Int {
    return when (res) {
        "snapit" -> R.drawable.ic_snap_it
        "youtube" -> R.drawable.ic_youtube_channel
        "walkie" -> R.drawable.ic_walkie_talkie
        else -> 0
    }
}