package uk.adbsalam.portfolio.gallery.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.gallery.feature.GalleryMedia
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme

@Composable
internal fun GalleryFlow(
    size: MutableState<Dp>,
    media: GalleryMedia,
    onClick: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 10000.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = media.title,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(size.value),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                media.images.forEachIndexed { index, item ->
                    item {
                        Image(
                            modifier = Modifier
                                .clickable { onClick(media.title, index) }
                                .aspectRatio(4f / 3f)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = item),
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@PreviewLight
@Composable
internal fun GalleryFlowLightPreview() {
    Adb_Screen_Theme {
        GalleryFlow(
            size = remember { mutableStateOf(500.dp) },
            media = GalleryMedia.mockkGallery().first(),
            onClick = { _, _ -> /* unused */ }
        )
    }
}

@PreviewDark
@Composable
internal fun GalleryFlowDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        GalleryFlow(
            size = remember { mutableStateOf(100.dp) },
            media = GalleryMedia.mockkGallery().first(),
            onClick = { _, _ -> /* unused */ }
        )
    }
}