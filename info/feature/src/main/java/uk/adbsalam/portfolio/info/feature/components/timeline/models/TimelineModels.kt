package uk.adbsalam.portfolio.info.feature.components.timeline.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


data class LineParameters(
    val strokeWidth: Dp,
    val brush: Brush
)

data class CircleParameters(
    val radius: Dp,
    val backgroundColor: Color,
    val stroke: StrokeParameters? = null,
    @DrawableRes val icon: Int? = null
)

data class StrokeParameters(
    val color: Color,
    val width: Dp
)
