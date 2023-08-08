package uk.adbsalam.portfolio.videos.feature.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.videos.data.VideoData

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
    videoData: VideoData,
) {
    val player = remember { mutableStateOf<YouTubePlayer?>(null) }
    val setToPlay = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned { coordinates ->
                val offsetY = coordinates.positionInRoot().y
                if (offsetY < 1150 && offsetY > 120) {
                    setToPlay.value = true
                    player.value?.play()

                } else {
                    setToPlay.value = false
                    player.value?.pause()
                }
            }
    ) {

        VideoPlayerView(
            player = player,
            initialPlay = setToPlay,
            videoData = videoData
        )

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = videoData.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = videoData.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun VideoCardPreviewLight() {
    Adb_Theme {
        VideoCard(
            videoData = VideoData.createMock().first(),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoCardPreviewDark() {
    Adb_Theme(true) {
        VideoCard(
            videoData = VideoData.createMock().first(),
        )
    }
}