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
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param subTitle subtitle of Chart to be shown as label
 * @param percent percentage to reach with animation
 *
 * This creates a horizontal animated progressbar representing horizontal chart
 */
@Composable
internal fun HorizontalChart(
    subTitle: String,
    percent: Float
) {

    var percentFloat by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = null) {
        percentFloat = percent
    }

    val progressAnimation by animateFloatAsState(
        targetValue = percentFloat,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = ""
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

@PreviewLight
@Composable
@SnapIt
internal fun HorizontalChartPreviewLight() {
    Adb_Theme {
        HorizontalChart(
            subTitle = "Component",
            percent = 40f
        )
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun HorizontalChartPreviewDark() {
    Adb_Theme(isSystemDark = true) {
        HorizontalChart(
            subTitle = "Component",
            percent = 40f
        )
    }
}
