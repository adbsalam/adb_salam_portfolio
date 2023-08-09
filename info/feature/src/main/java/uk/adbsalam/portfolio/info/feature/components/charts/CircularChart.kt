package uk.adbsalam.portfolio.info.feature.components.charts

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param modifier Modifier to apply on chart
 * @param title title of card to add
 * @param subTitle subtitle of card to be used
 * @param icon icon drawable resource for current chart
 * @param percent max value to reach on this progressbar with animated float
 *
 * This will create an animated circular chart for given values
 */
@Composable
internal fun CircularChart(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    @DrawableRes icon: Int,
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = 20.dp,
                progress = progressAnimation,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = subTitle,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun CircularChartPreviewLight() {
    Adb_Theme {
        CircularChart(
            title = "Android",
            subTitle = "kotlin",
            icon = R.drawable.ic_kotlin,
            percent = 0.5f
        )
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun CircularChartPreviewDark() {
    Adb_Theme(isSystemDark = true) {
        CircularChart(
            title = "Android",
            subTitle = "kotlin",
            icon = R.drawable.ic_kotlin,
            percent = 0.5f
        )
    }
}
