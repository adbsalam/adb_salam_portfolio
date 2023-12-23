package uk.adbsalam.portfolio.navigation

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class FullScreenArgs(
    val title: String = "Croatia",
    val index: Int = 0
) : Parcelable

data class BlogScreenArgs(
    val query: String = ""
)