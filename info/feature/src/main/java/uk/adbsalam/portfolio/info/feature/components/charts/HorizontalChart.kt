package uk.adbsalam.portfolio.info.feature.components.charts

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.feature.R
import uk.adbsalam.portfolio.theming.compose_color
import uk.adbsalam.portfolio.theming.compose_color_light

@Composable
fun HorizontalChart(
    subTitle: String,
    percent: Float
) {

    var percentFloat by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = null) {
        percentFloat = percent
    }

    val progressAnimation by animateFloatAsState(
        targetValue = percentFloat,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    Row(modifier = Modifier.fillMaxWidth()) {

        Column(Modifier.weight(1f)) {
            Text(
                text = subTitle,
                style = MaterialTheme.typography.titleSmall
            )
        }

        Column(Modifier.weight(2f)) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth(),
                progress = progressAnimation,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }

    }
}