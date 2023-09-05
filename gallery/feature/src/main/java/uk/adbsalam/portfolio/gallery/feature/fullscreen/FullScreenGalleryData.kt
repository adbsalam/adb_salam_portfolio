package uk.adbsalam.portfolio.gallery.feature.fullscreen

import androidx.annotation.DrawableRes
import uk.adbsalam.portfolio.components.R


data class FullScreenMedia(
    val title: String,
    @DrawableRes val images: List<Int>
) {
    companion object {
        fun mockkGallery() = listOf(
            FullScreenMedia(
                title = "Croatia",
                images = listOf(
                    R.drawable.cr_1,
                    R.drawable.cr_2,
                    R.drawable.cr_3,
                    R.drawable.cr_4,
                    R.drawable.cr_5,
                    R.drawable.cr_6,
                    R.drawable.cr_7,
                    R.drawable.cr_8,
                    R.drawable.cr_9,
                    R.drawable.cr_10,
                    R.drawable.cr_11,
                    R.drawable.cr_12,
                    R.drawable.cr_13,
                    R.drawable.cr_14,
                    R.drawable.cr_15
                )
            ),
            FullScreenMedia(
                title = "Anime Collections",
                images = listOf(
                    R.drawable.anim_1,
                    R.drawable.anim_2,
                    R.drawable.anim_3,
                    R.drawable.anim_4,
                    R.drawable.anim_5,
                    R.drawable.anim_6,
                    R.drawable.anim_7,
                )
            ),
            FullScreenMedia(
                title = "Switzerland",
                images = listOf(
                    R.drawable.swiss_1,
                    R.drawable.swiss_2,
                    R.drawable.swiss_3,
                    R.drawable.swiss_4,
                    R.drawable.swiss_5,
                    R.drawable.swiss_6,
                    R.drawable.swiss_7,
                    R.drawable.swiss_8,
                    R.drawable.swiss_9,
                    R.drawable.swiss_10,
                    R.drawable.swiss_11,
                    R.drawable.swiss_1,
                    R.drawable.swiss_2,
                    R.drawable.swiss_3,
                    R.drawable.swiss_4,
                    R.drawable.swiss_5,
                    R.drawable.swiss_6,
                    R.drawable.swiss_7,
                    R.drawable.swiss_8,
                    R.drawable.swiss_9,
                    R.drawable.swiss_10,
                    R.drawable.swiss_11,
                )
            )
        )
    }
}