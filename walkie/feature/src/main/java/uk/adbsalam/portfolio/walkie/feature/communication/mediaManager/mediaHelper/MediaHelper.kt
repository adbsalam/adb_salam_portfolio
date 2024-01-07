package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.mediaHelper

import java.io.File
import java.io.FileInputStream
import java.io.IOException

fun convertMP3ToByteArray(file: File?): ByteArray? {
    return try {
        if (file == null) return null
        val inputStream = FileInputStream(file)
        val length = inputStream.available()
        val byteArray = ByteArray(length)
        inputStream.read(byteArray)
        inputStream.close()
        byteArray
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}