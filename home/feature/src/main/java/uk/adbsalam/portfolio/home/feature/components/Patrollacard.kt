package uk.adbsalam.portfolio.home.feature.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun PatrollaCard(
    action: () -> Unit,
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lotti_app_patrolla))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Integer.MAX_VALUE,
        isPlaying = true
    )

    val readMore = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            Text(
                text = "Android",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            InfoText(
                title = "Patrolla Android App",
                body = stringResource(id = uk.adbsalam.portfolio.theming.R.string.patrolla_detail),
                expanded = readMore
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = action,
                    content = { Text(text = "View") }
                )

                TextButton(
                    content = { Text(text = "Read more") },
                    onClick = { readMore.value = !readMore.value }
                )
            }
        }
    }
}


@Composable
@Preview
fun PatrollaCardView() {
    Adb_Theme {
        PatrollaCard(
            action = {/* unused */ }
        )
    }
}

@Composable
@Preview
fun PatrollaCardDarkView() {
    Adb_Theme(isSystemDark = true) {
        PatrollaCard(
            action = {/* unused */ }
        )
    }
}
