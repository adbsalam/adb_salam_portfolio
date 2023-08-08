package uk.adbsalam.portfolio.theming.appbackground.snow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.unit.IntSize
import kotlin.math.cos
import kotlin.math.sin

/**
 * Snowflake paint to be use as color and fill style
 */
private val snowflakePaint = Paint().apply {
    isAntiAlias = true
    color = Color.White
    style = PaintingStyle.Fill
}

/**
 * @param incrementFactor incremental factor to setup snow flake speed
 * @param size size of sow flake to render
 * @param canvasSize total size of canvas
 * @param position starting position for snow flake
 * @param angle angle to direct flake at
 *
 * This will generate a snow flake along with animation path on canvas
 */
internal class Snowflake(
    private val incrementFactor: Float,
    private val size: Float,
    private val canvasSize: IntSize,
    position: Offset,
    angle: Double
) {
    private var position by mutableStateOf(position)
    private var angle by mutableStateOf(angle)
    fun update(elapsedMillis: Long) {
        val increment = (incrementFactor * elapsedMillis) / 2
        val xDelta = (increment * cos(angle)).toFloat()
        val yDelta = (increment * sin(angle)).toFloat()
        position = Offset(position.x + xDelta, position.y + yDelta)
        angle += angleSeedRange.random() / angleDivisor
        if (position.y > canvasSize.height + size) {
            position = Offset(position.x, -size)
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(position, size, snowflakePaint)
    }
}
