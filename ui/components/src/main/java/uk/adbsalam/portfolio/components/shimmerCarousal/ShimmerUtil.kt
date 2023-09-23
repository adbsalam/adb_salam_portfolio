package uk.adbsalam.portfolio.components.shimmerCarousal

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import uk.adbsalam.portfolio.components.R

const val SHIMMER_ANIMATION_DURATION = 500
const val SHIMMER_GRADIENTS_THRESHOLD = 0.97f

data class ShimmerCardItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val body: String
) {
    companion object {
        fun mockCards() = List(5) {
            ShimmerCardItem(
                R.drawable.thumb_swiss_2,
                "Test Title",
                "This is some cool animated shimmery text!!"
            )
        }
    }
}

class LazyRowShimmerState(
    val stateList: ArrayList<LazyRowItemShimmer> = arrayListOf()
) {
    data class LazyRowItemShimmer(
        val index: Int,
        val isShimmerComplete: Boolean
    )
}


@Composable
fun rememberShimmerAnimateAsFloat(
    targetValue: Float
) = animateFloatAsState(
    targetValue = targetValue,
    label = "",
    animationSpec = tween(SHIMMER_ANIMATION_DURATION),
)


@Composable
fun shimmerRadialBackground(
    alphaOne: Float,
    alphaTwo: Float,
    alphaThree: Float,
    index: Int
): Brush {
    return Brush.radialGradient(
        0.0F to MaterialTheme.colorScheme.secondary.copy(alphaOne),
        0.4F to MaterialTheme.colorScheme.secondary.copy(alphaTwo),
        0.6F to MaterialTheme.colorScheme.secondary.copy(alphaThree),
        1F to MaterialTheme.colorScheme.secondary.copy(alphaThree),
        center = if (index == 0) Offset(0f, 100f) else Offset(-900f, 200f),
        radius = if (index == 0) 900f else 3000f, //bigger circles for rest items, since start point of circle should be wider
        tileMode = TileMode.Decal
    )
}

@Composable
fun shimmerTextBackground(
    alphaOne: Float,
    alphaTwo: Float,
    alphaThree: Float,
): Brush {
    return Brush.linearGradient(
        0F to MaterialTheme.colorScheme.background.copy(alphaOne),
        0.25F to MaterialTheme.colorScheme.background.copy(alphaTwo),
        0.5F to MaterialTheme.colorScheme.background.copy(alphaThree),
        0.75F to MaterialTheme.colorScheme.background.copy(alphaThree),
        1F to MaterialTheme.colorScheme.background.copy(alphaThree),
    )
}