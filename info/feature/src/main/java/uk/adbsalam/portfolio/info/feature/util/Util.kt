package uk.adbsalam.portfolio.info.feature.util

import android.nfc.Tag
import androidx.annotation.DrawableRes
import uk.adbsalam.portfolio.components.R

@DrawableRes
internal fun workIcon(
    tag: String
): Int {

    println("------------------------" + tag)

    return when (tag) {
        "ic_android" -> R.drawable.ic_android
        "ic_kotlin" -> R.drawable.ic_kotlin
        "ic_compose" -> R.drawable.ic_compose_logo
        "ic_java" -> R.drawable.ic_java
        "ic_firebase" -> R.drawable.ic_firebase
        "ic_cplus" -> R.drawable.ic_c_plus
        "ic_wordpress" -> R.drawable.ic_wordpress
        "ic_bitbucket" -> R.drawable.ic_bit_bucket
        "ic_shop" -> R.drawable.ic_shop
        else -> 0
    }
}