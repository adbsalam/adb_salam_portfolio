package uk.adbsalam.portfolio.info.feature.components.infocards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.info.feature.components.InfoTitle
import uk.adbsalam.portfolio.info.feature.components.charts.CircularChart
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Top level chart to show Circular Chart
 * Animated progress will be shown
 */
@Composable
internal fun AndroidMainCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .statusBarsPadding()
            .adbRoundedBackground()
            .padding(14.dp),
    ) {

        InfoTitle(title = "Android Main")

        Row(modifier = Modifier.fillMaxWidth()) {
            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Android",
                subTitle = "Kotlin",
                icon = R.drawable.ic_kotlin,
                percent = 1f,
            )

            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Design",
                subTitle = "Compose",
                icon = R.drawable.ic_compose_logo,
                percent = 0.9f,
            )
        }
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun AndroidMainCardLight() {
    Adb_Theme {
        AndroidMainCard()
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun AndroidMainCardDark() {
    Adb_Theme(isSystemDark = true) {
        AndroidMainCard()
    }
}
