package uk.adbsalam.portfolio.gallery.feature.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.gallery.feature.GalleryMedia
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme

val MIN_SIZE = 100.dp
val MAX_SIZE = 300.dp

@Composable
internal fun GalleryGrid(
    gridCellSize: MutableState<Dp>,
    galleryMedia: List<GalleryMedia>,
    onTransforming: (Boolean) -> Unit,
    onClick: (String, Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 20.dp)
            .transformGrid(
                onTransforming = onTransforming,
                gridCellSize = gridCellSize
            )
    ) {
        galleryMedia.forEach {
            GalleryFlow(size = gridCellSize, media = it, onClick = onClick)
        }
    }
}

@PreviewLight
@Composable
internal fun GalleryGridLightPreview() {
    Adb_Screen_Theme {
        GalleryGrid(
            gridCellSize = remember { mutableStateOf(100.dp) },
            galleryMedia = GalleryMedia.mockkGallery(),
            onTransforming = { /* unused */ },
            onClick = { _, _ -> /* unused */ }
        )
    }
}

@PreviewDark
@Composable
internal fun GalleryGridDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        GalleryGrid(
            gridCellSize = remember { mutableStateOf(100.dp) },
            galleryMedia = GalleryMedia.mockkGallery(),
            onTransforming = { /* unused */ },
            onClick = { _, _ -> /* unused */ }
        )
    }
}

private fun Modifier.transformGrid(
    onTransforming: (Boolean) -> Unit,
    gridCellSize: MutableState<Dp>
) = this.pointerInput(null) {
    awaitEachGesture {
        do {
            val event = awaitPointerEvent()
            val pointCount = event.changes.size
            val isTransforming = pointCount > 1
            onTransforming(isTransforming)
            if (isTransforming) {
                gridCellSize.value = gridCellSize.value
                    .times(event.calculateZoom())
                    .coerceIn(
                        minimumValue = MIN_SIZE,
                        maximumValue = MAX_SIZE
                    )
            }
        } while (event.changes.any { it.pressed })
    }
}