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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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
    index: Int,
    videoData: VideoData,
    play: Boolean,
    onPosition: (y: Float) -> Unit
) {

    var playNow by remember { mutableStateOf(false) }
    var yCo by remember { mutableStateOf(0f) }
    val localContext = LocalContext.current
    val view = YouTubePlayerView(localContext)
    var yblayer: YouTubePlayer? = null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->


                if(yCo < 1150 && yCo > 120){
                    yblayer?.play()
                }
                else{
                    yblayer?.pause()
                }
                yCo = coordinates.positionInRoot().y
                playNow = coordinates.positionInRoot().y > 1000f
                onPosition(coordinates.positionInRoot().y)


                if(index == 0){
                    println("---------------------------- " + yCo)
                }
            }
            .padding(horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
        ) {
            AndroidView(
                factory = {
                    val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
                        .controls(0)
                        .mute(1)
                        .build()
                    view.enableAutomaticInitialization = false
                    val customView = view.inflateCustomPlayerUi(R.layout.player_item_view)
                    view.initialize(
                        youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {

                                yblayer = youTubePlayer

                                val customPlayerUiController = CustomPlayerUiController(
                                    localContext,
                                    customView,
                                    youTubePlayer,
                                    view
                                )

                                youTubePlayer.addListener(customPlayerUiController);

                                customPlayerUiController.ready()
                                youTubePlayer.cueVideo(videoData.videoId, 0f)

                                if (playNow) {
                                    youTubePlayer.play()
                                }

                            }
                        },
                        playerOptions = iFramePlayerOptions
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

@Preview
@Composable
fun PlayerPreview() {
    Player(
        index = 0,
        videoData = VideoData.createMock().first(),
        play = false,
        onPosition = {}
    )
}