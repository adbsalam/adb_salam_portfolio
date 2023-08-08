package uk.adbsalam.portfolio.videos.feature.components

import android.content.Context
import android.view.View
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar
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
    private val initPlay: Boolean
) : AbstractYouTubePlayerListener() {

    private lateinit var panel: View
    private lateinit var progressbar: View
    private lateinit var seekbar: YouTubePlayerSeekBar
    private var counter = 0

    init {
        youTubePlayer.addListener(playerTracker)
        initViews(customPlayerUi)
    }

    private fun initViews(playerUi: View) {
        panel = playerUi.findViewById(R.id.panel)
        progressbar = playerUi.findViewById(R.id.progressbar)
        seekbar = playerUi.findViewById(R.id.youtube_player_seekbar)
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerState) {
        when (state) {
            PlayerState.PLAYING,
            PlayerState.PAUSED,
            PlayerState.VIDEO_CUED -> {
                progressbar.visibility = View.GONE
                panel.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.transparent
                    )
                )
                if (initPlay && counter == 0) {
                    youTubePlayer.play()
                    counter++
                }
            }

            else -> {}
        }

        seekbar.visibility = if (state == PlayerState.PLAYING) VISIBLE else View.INVISIBLE
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        seekbar.onCurrentSecond(youTubePlayer, second)
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        seekbar.onVideoDuration(youTubePlayer, duration)
    }

}