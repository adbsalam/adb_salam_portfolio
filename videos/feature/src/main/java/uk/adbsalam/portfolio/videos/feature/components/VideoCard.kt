package uk.adbsalam.portfolio.videos.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.videos.data.objects.VideoItems
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param videoData video card to populate on screen
 *
 * This will handle auto play of videos when in playable screen area
 * which currently is between 1150f to 120f
 * As soon as video reaches top of page near status bar, video will pause
 * Video will start playing while between playable area
 */
@Composable
internal fun VideoCard(
    videoData: VideoItems.Video,
    isInitialized: Boolean,
    onInitialized: () -> Unit,
) {
    val player = remember { mutableStateOf<YouTubePlayer?>(null) }
    val setToPlay = remember { mutableStateOf(false) }
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .onGloballyPositioned { coordinates ->
                    val offsetY = coordinates.positionInRoot().y
                    if (offsetY < 1150 && offsetY > 120 && isInitialized) {
                        setToPlay.value = true
                        player.value?.play()
                    } else {
                        setToPlay.value = false
                        player.value?.pause()
                    }
                },
    ) {
        if (isInitialized) {
            VideoPlayerView(
                player = player,
                initialPlay = setToPlay,
                videoData = videoData,
            )
        } else {
            Box(
                modifier =
                    Modifier
                        .aspectRatio(16f / 9f)
                        .clickable(
                            onClick = {
                                onInitialized()
                            },
                        ),
                contentAlignment = Alignment.Center,
            ) {
                val request =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://img.youtube.com/vi/${videoData.videoId}/hqdefault.jpg")
                        .crossfade(true)
                        .build()
                AsyncImage(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    model = request,
                    contentDescription = null,
                )
                Image(
                    modifier =
                        Modifier
                            .size(48.dp),
                    painter = painterResource(uk.adbsalam.portfolio.components.R.drawable.ic_youtube),
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = videoData.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = videoData.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun VideoCardPreviewLight() {
    Adb_Theme {
        VideoCard(
            videoData = VideoItems.createMock().videos.first(),
            onInitialized = {},
            isInitialized = false,
        )
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun VideoCardPreviewDark() {
    Adb_Theme(true) {
        VideoCard(
            videoData = VideoItems.createMock().videos.first(),
            onInitialized = {},
            isInitialized = false,
        )
    }
}
