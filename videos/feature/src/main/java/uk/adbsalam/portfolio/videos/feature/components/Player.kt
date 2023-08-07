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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
    play: Boolean
) {

    var positionInRootTopBar by remember { mutableStateOf(Offset.Zero) }
    val config = LocalConfiguration.current

    // Get local density from composable
    val localDensity = LocalDensity.current

    // Create element height in pixel state
    var columnHeightPx by remember {
        mutableStateOf(0f)
    }

    // Create element height in dp state
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinaties ->
                positionInRootTopBar = coordinaties.positionInWindow()

                columnHeightPx = coordinaties.size.height.toFloat()
                columnHeightDp = with(localDensity) { coordinaties.size.height.toDp() }

                if(index == 1){

                    println("------------------------------------" + columnHeightPx)
                    println("------------------------------------" + columnHeightDp)
                }
//                if( positionInRootTopBar.y < 500 ){
//                    println("-------------------------------- in view-----------" + index )
//                }

            }
            .padding(horizontal = 12.dp)
    ) {

        val localContext = LocalContext.current
        val view = YouTubePlayerView(localContext)

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
                                val customPlayerUiController = CustomPlayerUiController(
                                    localContext,
                                    customView,
                                    youTubePlayer,
                                    view
                                )

                                youTubePlayer.addListener(customPlayerUiController);

                                customPlayerUiController.ready()
                                youTubePlayer.cueVideo(videoData.videoId, 0f)
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
        play = false
    )
}