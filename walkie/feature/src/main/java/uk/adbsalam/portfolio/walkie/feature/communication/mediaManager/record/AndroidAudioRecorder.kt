package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.record

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.mediaHelper.convertMP3ToByteArray
import java.io.File
import java.io.FileOutputStream

class AndroidAudioRecorder : AudioRecorder {

    private var recorder: MediaRecorder? = null
    private var audioFile: File? = null

    override fun start(context: Context) {
        audioFile = File(context.cacheDir, "audio.mp3")
        recorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()

        recorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(audioFile).fd)
            prepare()
            start()
        }
    }

    override fun stopAndGetRecording(): ByteArray? {
        recorder?.stop()
        recorder?.reset()
        recorder = null
        val dataAsBytes = convertMP3ToByteArray(audioFile)
        audioFile = null
        return dataAsBytes
    }
}