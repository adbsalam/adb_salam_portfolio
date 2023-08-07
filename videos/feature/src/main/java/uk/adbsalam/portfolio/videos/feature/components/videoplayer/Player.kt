package uk.adbsalam.portfolio.videos.feature.components.videoplayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import uk.adbsalam.portfolio.videos.feature.R
import uk.adbsalam.portfolio.videos.feature.utils.VideoData

@Composable
fun VideoPlayerView(
    player: MutableState<YouTubePlayer?>,
    initialPlay: MutableState<Boolean>,
    videoData: VideoData,
) {
    val context = LocalContext.current
    val playerTracker = YouTubePlayerTracker()
    val view = YouTubePlayerView(context)

    AndroidView(
        factory = {
            view.enableAutomaticInitialization = false
            val customView = view.inflateCustomPlayerUi(R.layout.player_item_view)
            view.initialize(
                youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        player.value = youTubePlayer
                        val customPlayerUiController = CustomPlayerUiController(
                            context = context,
                            customPlayerUi = customView,
                            playerTracker = playerTracker,
                            youTubePlayer = youTubePlayer,
                            initPlay = initialPlay.value
                        )
                        youTubePlayer.addListener(customPlayerUiController);
                        customPlayerUiController.ready()
                        youTubePlayer.cueVideo(videoData.videoId, 0f)
                    }
                },
                playerOptions = iFrameOptions()
            )
            view
        }
    )
}

private fun iFrameOptions(): IFramePlayerOptions {
    return IFramePlayerOptions.Builder()
        .controls(0)
        .mute(1)
        .build()
}
