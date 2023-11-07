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

const val SHIMMER_ANIMATION_DURATION = 300
const val SHIMMER_GRADIENTS_THRESHOLD = 0.97f

data class ShimmerCardItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val body: String,
    val deepLink: String,
) {
    companion object {
        fun mockCards() = listOf(
            ShimmerCardItem(
                R.drawable.shimm,
                "Synchronised Shimmer",
                "Shimmer skeleton loader reinvented, Have a look at this sample",
                "/shimmer_card"
            ),

            ShimmerCardItem(
                R.drawable.transform,
                "Transformable Gallery",
                "Transformation, grid arrangement selection and fancy craousals and this is some extra long text",
                "/gallery"
            ),

            ShimmerCardItem(
                R.drawable.animation_samples,
                "Animation Experiments",
                "Some of animation components visualised in form of graphs asdasd asd asd asd as das da sdasdasd asd asd asd as da sd asd as da sd asd ",
                "/animation_samples"
            ),

            ShimmerCardItem(
                R.drawable.dark_mode,
                "Explore Dark Mode",
                "Insights of how dark mode for this app is implementedasda as d asd   asdasd",
                "/dark_mode"
            ),

            ShimmerCardItem(
                R.drawable.placeholder,
                "Placeholder sample",
                "Once I ll figure out what to add here, will add!",
                "/placeholder"
            )
        )
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