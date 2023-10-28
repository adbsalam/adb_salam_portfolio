package uk.adbsalam.portfolio.samples.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.AsyncUpdates
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun InProgressPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(uk.adbsalam.portfolio.components.R.raw.ic_in_progress))

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                isPlaying = true,
                restartOnPlay = false,
                iterations = Int.MAX_VALUE,
                renderMode = RenderMode.HARDWARE,
                asyncUpdates = AsyncUpdates.ENABLED,
                modifier = Modifier.size(200.dp)
            )
            Text(text = "Currently in progress. Please check again later.")
        }
    }
}