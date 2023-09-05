package uk.adbsalam.portfolio.gallery.feature

import androidx.annotation.DrawableRes
import uk.adbsalam.portfolio.components.R

@DrawableRes
val highlights = listOf(
    R.drawable.thumb_swiss_1,
    R.drawable.thumb_swiss_2,
    R.drawable.thumb_swiss_3,
    R.drawable.thumb_swiss_4,
    R.drawable.thumb_swiss_5,
    R.drawable.thumb_swiss_6,
    R.drawable.thumb_swiss_3,
    R.drawable.thumb_swiss_4,
)

@DrawableRes
val recent = listOf(
    R.drawable.thumb_cr_3,
    R.drawable.thumb_cr_8,
    R.drawable.thumb_swiss_5,
    R.drawable.thumb_swiss_6,
    R.drawable.thumb_cr_9,
    R.drawable.thumb_cr_10,
    R.drawable.thumb_cr_11,
    R.drawable.thumb_anim_3,
    R.drawable.thumb_anim_5,
    R.drawable.thumb_anim_7,
    R.drawable.thumb_cr_6,
)

data class GalleryMedia(
    val title: String,
    @DrawableRes val images: List<Int>
) {
    companion object {
        fun mockkGallery() = listOf(
            GalleryMedia(
                title = "Croatia",
                images = listOf(
                    R.drawable.thumb_cr_1,
                    R.drawable.thumb_cr_2,
                    R.drawable.thumb_cr_3,
                    R.drawable.thumb_cr_4,
                    R.drawable.thumb_cr_5,
                    R.drawable.thumb_cr_6,
                    R.drawable.thumb_cr_7,
                    R.drawable.thumb_cr_8,
                    R.drawable.thumb_cr_9,
                    R.drawable.thumb_cr_10,
                    R.drawable.thumb_cr_11,
                    R.drawable.thumb_cr_12,
                    R.drawable.thumb_cr_13,
                    R.drawable.thumb_cr_14,
                    R.drawable.thumb_cr_15
                )
            ),
            GalleryMedia(
                title = "Anime Collections",
                images = listOf(
                    R.drawable.thumb_anim_1,
                    R.drawable.thumb_anim_2,
                    R.drawable.thumb_anim_3,
                    R.drawable.thumb_anim_4,
                    R.drawable.thumb_anim_5,
                    R.drawable.thumb_anim_6,
                    R.drawable.thumb_anim_7,
                )
            ),
            GalleryMedia(
                title = "Switzerland",
                images = listOf(
                    R.drawable.thumb_swiss_1,
                    R.drawable.thumb_swiss_2,
                    R.drawable.thumb_swiss_3,
                    R.drawable.thumb_swiss_4,
                    R.drawable.thumb_swiss_5,
                    R.drawable.thumb_swiss_6,
                    R.drawable.thumb_swiss_7,
                    R.drawable.thumb_swiss_8,
                    R.drawable.thumb_swiss_9,
                    R.drawable.thumb_swiss_10,
                    R.drawable.thumb_swiss_11,
                    R.drawable.thumb_swiss_1,
                    R.drawable.thumb_swiss_2,
                    R.drawable.thumb_swiss_3,
                    R.drawable.thumb_swiss_4,
                    R.drawable.thumb_swiss_5,
                    R.drawable.thumb_swiss_6,
                    R.drawable.thumb_swiss_7,
                    R.drawable.thumb_swiss_8,
                    R.drawable.thumb_swiss_9,
                    R.drawable.thumb_swiss_10,
                    R.drawable.thumb_swiss_11,
                )
            )
        )
    }
}