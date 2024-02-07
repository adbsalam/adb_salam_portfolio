package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.playback

import android.content.Context

interface AudioPlayer {
    fun playFile(context: Context, data: ByteArray, onAudioComplete: () -> Unit)
}