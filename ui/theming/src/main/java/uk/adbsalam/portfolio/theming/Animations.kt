package uk.adbsalam.portfolio.theming

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import java.time.Duration

fun enterFadeAndSlide(directionY: Int = 0, duration: Int = 500): EnterTransition {
    return slideInVertically(
        animationSpec = tween(duration),
        initialOffsetY = { if (directionY != 0) directionY else -it / 2 }
    ) + fadeIn(tween(duration))
}

fun exitFadeAndSlide(directionY: Int = 0, duration: Int = 500): ExitTransition {
    return slideOutVertically(
        animationSpec = tween(duration),
        targetOffsetY = { if (directionY != 0) directionY else -it / 2 }
    ) + fadeOut(tween(duration))
}