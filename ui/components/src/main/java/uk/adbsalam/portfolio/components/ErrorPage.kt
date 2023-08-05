package uk.adbsalam.portfolio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ErrorPage(
    msg: String = "Something went wrong, please retry",
    retry: () -> Unit
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_error))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Integer.MAX_VALUE,
        isPlaying = true
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .width(140.dp)
                .height(140.dp)
        )
        Text(text = msg)
        Button(onClick = { retry() }) {
            Text(text = "Retry")
        }

    }
}

@Preview
@Composable
fun ErrorPagePreview() {
    ErrorPage(
        msg = "Cannot load page currently",
        retry = { /*unused*/ }
    )
}