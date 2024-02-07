package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(context: Context, data: ByteArray, onAudioComplete: () -> Unit) {
        val file = File(context.cacheDir, "recieved.mp3")
        file.writeBytes(data)
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
            setOnCompletionListener {
                onAudioComplete()
                player?.stop()
                player?.release()
                player = null
            }
        }
    }
}