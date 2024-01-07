package uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.mediaHelper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import com.google.android.gms.nearby.connection.Payload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.onVolumeButton(
    onStartRecording: () -> Unit,
    onStopAndGetRecording: () -> ByteArray?,
    onSendAudio: (Payload) -> Unit,
) = composed {

    var consumeKeyDown by remember { mutableStateOf(false) }

    Modifier.onKeyEvent { keyEvent ->
        return@onKeyEvent when{
            keyEvent.type == KeyEventType.KeyDown && keyEvent.key == Key.VolumeDown -> {
                if (!consumeKeyDown) { onStartRecording() }
                consumeKeyDown = true
                true
            }
            keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.VolumeDown -> {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    val audioFile = onStopAndGetRecording()
                    audioFile?.let { onSendAudio(Payload.fromBytes(it)) }
                }
                consumeKeyDown = false
                true
            }
            else -> false
        }
    }
}