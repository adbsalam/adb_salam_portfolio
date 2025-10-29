package uk.adbsalam.portfolio.videos.feature.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.VISIBLE
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBarListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import uk.adbsalam.portfolio.videos.feature.R

/**
 * @param context context to be passed from view
 * @param customPlayerUi custom ui player ui as from XML
 * @param playerTracker player tracker to be used for video timeline tracking
 * @param youTubePlayer current youtube player instance
 * @param initPlay start as soon as created true or false
 *
 * This is custom UI for Video player to show in list
 * Add functionality to custom UI here
 */
internal class CustomPlayerUiController(
    private val context: Context,
    customPlayerUi: View,
    playerTracker: YouTubePlayerTracker,
    youTubePlayer: YouTubePlayer,
    private val initPlay: Boolean,
) : AbstractYouTubePlayerListener() {
    private lateinit var panel: View
    private lateinit var progressbar: View
    private lateinit var seekbar: YouTubePlayerSeekBar
    private lateinit var composeView: ComposeView
    private var counter = 0

    init {
        youTubePlayer.addListener(playerTracker)
        initViews(customPlayerUi)
    }

    private fun initViews(playerUi: View) {
        panel = playerUi.findViewById(R.id.panel)
        progressbar = playerUi.findViewById(R.id.progressbar)
        seekbar = playerUi.findViewById(R.id.youtube_player_seekbar)
        composeView = playerUi.findViewById(R.id.youtube_icon)
    }

    override fun onStateChange(
        youTubePlayer: YouTubePlayer,
        state: PlayerState,
    ) {
        panel.setOnClickListener {
            if (state == PlayerState.PLAYING) {
                youTubePlayer.pause()
            } else {
                youTubePlayer.play()
            }
        }
        when (state) {
            PlayerState.PLAYING,
            PlayerState.PAUSED,
            PlayerState.VIDEO_CUED,
                -> {
                youTubePlayer.addListener(seekbar)
                seekbar.youtubePlayerSeekBarListener = object : YouTubePlayerSeekBarListener {
                    override fun seekTo(time: Float) {
                        youTubePlayer.seekTo(time)
                    }
                }

                progressbar.visibility = View.GONE
                panel.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.transparent,
                    ),
                )
                if (initPlay && counter == 0) {
                    youTubePlayer.play()
                    counter++
                }
            }

            else -> {}
        }

        seekbar.visibility =
            if (state == PlayerState.PLAYING || state == PlayerState.PAUSED) VISIBLE else View.INVISIBLE
        composeView.visibility =
            if (state == PlayerState.PLAYING || state == PlayerState.PAUSED) VISIBLE else View.INVISIBLE
    }

    fun setOnClick(videoId: String) {
        composeView.setContent {
            Row(
                modifier = Modifier.clickable(
                    onClick = {
                        val url = "https://www.youtube.com/watch?v=$videoId"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("watch on ", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Image(
                    modifier = Modifier
                        .size(32.dp),
                    painter = painterResource(uk.adbsalam.portfolio.components.R.drawable.ic_youtube),
                    contentDescription = null
                )
            }

        }
    }

    override fun onCurrentSecond(
        youTubePlayer: YouTubePlayer,
        second: Float,
    ) {
        seekbar.onCurrentSecond(youTubePlayer, second)
    }

    override fun onVideoDuration(
        youTubePlayer: YouTubePlayer,
        duration: Float,
    ) {
        seekbar.onVideoDuration(youTubePlayer, duration)
    }
}
