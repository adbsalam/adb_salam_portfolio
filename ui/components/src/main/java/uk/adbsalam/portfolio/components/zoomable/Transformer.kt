package uk.adbsalam.portfolio.components.zoomable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize

private const val ZOOM_MIN = 1.0f
private const val ZOOM_MAX = 5.0f
private val panZero = Offset(0f, 0f)

fun Modifier.transformer(
    resetTransformation: MutableState<Boolean>,
    contentSize: MutableState<IntSize>,
    onTap: () -> Unit,
    onTransForming: (Boolean) -> Unit
): Modifier = composed {

    var zoom by remember { mutableStateOf(ZOOM_MIN) }
    var pan by remember { mutableStateOf(panZero) }
    var animateReset by remember { mutableStateOf(false) }
    var containerSize by remember { mutableStateOf(IntSize(0, 0)) }

    val zoomAnimate = animateFloatAsState(
        targetValue = zoom,
        label = "animateZoom",
        finishedListener = { animateReset = false }

    )

    val panAnimate = animateOffsetAsState(
        targetValue = pan,
        label = "animateZoom",
        finishedListener = { animateReset = false }
    )

    if (resetTransformation.value) {
        animateReset = true
        zoom = ZOOM_MIN
        pan = panZero
        resetTransformation.value = false
        onTransForming(false)
    }

    this
        .onSizeChanged { containerSize = it }
        .pointerInput(Unit) {
            awaitEachGesture {
                do {
                    val event = awaitPointerEvent()
                    val pointCount = event.changes.size
                    onTransForming(pointCount > 1 || zoom > 1f)
                } while (event.changes.any { it.pressed })
            }
        }
        .pointerInput(Unit) {
            detectTransformGestures { _, panChange, zoomChange, _ ->

                zoom = zoom
                    .times(zoomChange)
                    .coerceIn(ZOOM_MIN, ZOOM_MAX)

                val newOffset = pan + panChange.times(zoom / 2)

                val contentScaledWidth = contentSize.value.width * zoom
                val contentScaledHeight = contentSize.value.height * zoom

                val maxY =
                    if (contentScaledHeight <= containerSize.height) 0f
                    else contentScaledHeight
                        .minus(containerSize.height)
                        .div(2)

                val maxX =
                    if (contentScaledWidth <= containerSize.width) 0f
                    else contentScaledWidth
                        .minus(containerSize.width)
                        .div(2)

                pan = Offset(
                    newOffset.x.coerceIn(-maxX, maxX),
                    newOffset.y.coerceIn(-maxY, maxY),
                )
            }
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { onTap() },
                onDoubleTap = {
                    animateReset = true
                    zoom = if (zoom <= ZOOM_MIN) 2f else ZOOM_MIN
                    pan = panZero
                }
            )
        }
        .graphicsLayer {
            val currentZoom = if (animateReset) zoomAnimate.value else zoom
            val currentPan = if (animateReset) panAnimate.value else pan

            scaleX = currentZoom
            scaleY = currentZoom
            translationX = currentPan.x
            translationY = currentPan.y
        }
}