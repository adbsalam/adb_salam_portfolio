package uk.adbsalam.portfolio.startup.feature.components.snow

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import java.util.concurrent.ThreadLocalRandom

internal const val snowflakeDensity = 0.004
internal val incrementRange = 0.4f..0.8f
internal val sizeRange = 5.0f..12.0f
internal const val angleSeed = 25.0f
internal const val angleRange = 0.1f
internal const val angleDivisor = 10000.0f
internal val angleSeedRange = -angleSeed..angleSeed

fun ClosedRange<Float>.random() =
    ThreadLocalRandom.current().nextFloat() * (endInclusive - start) + start

fun Float.random() = ThreadLocalRandom.current().nextFloat() * this

fun Int.random() = ThreadLocalRandom.current().nextInt(this)

fun IntSize.randomPosition() = Offset(width.random().toFloat(), height.random().toFloat())