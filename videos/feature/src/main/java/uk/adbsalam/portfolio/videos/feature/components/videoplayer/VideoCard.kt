package uk.adbsalam.portfolio.videos.feature.components.videoplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
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
import uk.adbsalam.portfolio.videos.feature.utils.VideoData

@Composable
fun VideoCard(
    videoData: VideoData,
) {

    val player = remember { mutableStateOf<YouTubePlayer?>(null) }
    val setToPlay = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
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
}

@Preview
@Composable
fun VideoCardPreview() {
    VideoCard(
        videoData = VideoData.createMock().first(),
    )
}