package uk.adbsalam.portfolio.videos.feature.components

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import uk.adbsalam.portfolio.videos.feature.R


data class VideoData(
    val title: String,
    val description: String,
    val videoId: String
) {
    companion object {
        fun createMock() = listOf(
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "DE5o92ya25I"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "OFqTWt4OnLE"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "MS-CiBqGpbg"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "rYc5O8mSuF4"
            )
        )
    }
}

@Composable
fun Player(
    videoData: VideoData,
) {
    val localContext = LocalContext.current
    val view = YouTubePlayerView(localContext)
    var yblayer: YouTubePlayer? = null
    val playerTracker = YouTubePlayerTracker()
    var setToPlay = false

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .onGloballyPositioned { coordinates ->
                val offsetY = coordinates.positionInRoot().y
                if (offsetY < 1150 && offsetY > 120) {
                    setToPlay = true
                    yblayer?.play()
                } else {
                    setToPlay = false
                    yblayer?.pause()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
        ) {
            AndroidView(
                factory = {
                    view.enableAutomaticInitialization = false
                    val customView = view.inflateCustomPlayerUi(R.layout.player_item_view)
                    view.initialize(
                        youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                yblayer = youTubePlayer
                                val customPlayerUiController = CustomPlayerUiController(
                                    context = localContext,
                                    customPlayerUi = customView,
                                    playerTracker = playerTracker,
                                    youTubePlayer = youTubePlayer,
                                    setToPlay
                                )
                                youTubePlayer.addListener(customPlayerUiController);
                                customPlayerUiController.ready()
                                youTubePlayer.cueVideo(videoData.videoId, 0f)
                            }
                        },
                        playerOptions = iFrameOptions()
                    )
                    view
                })

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

fun iFrameOptions(): IFramePlayerOptions {
    return IFramePlayerOptions.Builder()
        .controls(0)
        .mute(1)
        .build()
}

@Preview
@Composable
fun PlayerPreview() {
    Player(
        videoData = VideoData.createMock().first(),
    )
}