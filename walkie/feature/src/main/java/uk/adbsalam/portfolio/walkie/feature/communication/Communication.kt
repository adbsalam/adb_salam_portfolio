package uk.adbsalam.portfolio.walkie.feature.communication

import androidx.annotation.RawRes
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.nearby.connection.Payload
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.mediaHelper.onVolumeButton
import uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.playback.AndroidAudioPlayer
import uk.adbsalam.portfolio.walkie.feature.communication.mediaManager.record.AndroidAudioRecorder

@Composable
fun Communication(
    userName: String,
    sendAudio: (Payload) -> Unit,
    audioPayload: Payload?
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val recorder by remember { mutableStateOf(AndroidAudioRecorder()) }
    val player by remember { mutableStateOf(AndroidAudioPlayer()) }

    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(audioPayload) {
        audioPayload?.let { payload ->
            val data = payload.asBytes()
            data?.let {
                player.playFile(context, it) { isPlaying = false }
                isPlaying = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .focusRequester(focusRequester)
            .focusable()
            .padding(20.dp)
            .onVolumeButton(
                onStartRecording = {
                    isPlaying = false
                    isRecording = true
                    recorder.start(context)
                },
                onStopAndGetRecording = {
                    isRecording = false
                    recorder.stopAndGetRecording()
                },
                onSendAudio = sendAudio
            )
    ) {
        CommunicationsLottie(
            isPlaying = isPlaying,
            res = R.raw.ic_sound_wave,
            lottieModifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = "Connected To: $userName",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Press and hold volume down button to communicate",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }

        CommunicationsLottie(
            isPlaying = isRecording,
            res = R.raw.ic_mic,
            lottieModifier = Modifier
                .width(200.dp)
                .height(160.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

    }
}

@Composable
private fun CommunicationsLottie(
    modifier: Modifier,
    isPlaying: Boolean,
    @RawRes res: Int,
    lottieModifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = Integer.MAX_VALUE,
            restartOnPlay = true,
            isPlaying = isPlaying,
            cancellationBehavior = LottieCancellationBehavior.Immediately
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = lottieModifier
                .align(Alignment.Center)
        )
    }
}

@PreviewLight
@Composable
private fun CommunicationPreview() {
    Adb_Screen_Theme {
        Communication(
            userName = "3452",
            sendAudio = { /* unused */ },
            audioPayload = null
        )
    }
}

@PreviewDark
@Composable
private fun CommunicationPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Communication(
            userName = "3452",
            sendAudio = { /* unused */ },
            audioPayload = null
        )
    }
}

