package uk.adbsalam.portfolio.videos.feature.components

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import uk.adbsalam.portfolio.videos.feature.R
import java.text.SimpleDateFormat
import java.util.Date


internal class CustomPlayerUiController(
    private val context: Context,
    customPlayerUi: View,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) :
    AbstractYouTubePlayerListener() {
    private val playerTracker: YouTubePlayerTracker = YouTubePlayerTracker()
    private var panel: View? = null
    private var progressbar: View? = null
    private var btPlay: Button? = null
    private var seekbar: YouTubePlayerSeekBar? = null


    init {
        youTubePlayer.addListener(playerTracker)
        initViews(customPlayerUi)
    }

    private fun initViews(playerUi: View) {
        panel = playerUi.findViewById(R.id.panel)
        progressbar = playerUi.findViewById(R.id.progressbar)
        btPlay = playerUi.findViewById(R.id.btn_play)
        seekbar = playerUi.findViewById(R.id.youtube_player_seekbar)

        btPlay!!.setOnClickListener{

            if(playerTracker.state == PlayerConstants.PlayerState.PLAYING){
                seekbar?.visibility  = View.INVISIBLE
                youTubePlayer.pause()
            }
            else{
                youTubePlayer.play()
                seekbar?.visibility  = View.VISIBLE
            }
        }
    }

    fun ready() {
        progressbar!!.visibility = View.GONE
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        if (state == PlayerConstants.PlayerState.PLAYING || state == PlayerConstants.PlayerState.PAUSED || state == PlayerConstants.PlayerState.VIDEO_CUED) panel!!.setBackgroundColor(
            ContextCompat.getColor(
                context, R.color.transparent
            )
        ) else if (state == PlayerConstants.PlayerState.BUFFERING) panel!!.setBackgroundColor(
            ContextCompat.getColor(
                context, R.color.transparent
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        seekbar?.onCurrentSecond(youTubePlayer, second)
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        seekbar?.onVideoDuration(youTubePlayer, duration)
    }

}