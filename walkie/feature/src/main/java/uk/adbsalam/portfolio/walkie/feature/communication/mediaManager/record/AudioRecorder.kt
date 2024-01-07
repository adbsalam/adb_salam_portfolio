package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.record

import android.content.Context

interface AudioRecorder {
    fun start(context: Context)
    fun stopAndGetRecording(): ByteArray?
}