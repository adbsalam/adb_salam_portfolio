package uk.adbsalam.portfolio.info.feature.components.infocards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
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
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .adbRoundedBackground()
                    .padding(vertical = 10.dp)
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Android",
                    subTitle = "Kotlin",
                    icon = R.drawable.ic_kotlin,
                    percent = 1f,
                )
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .adbRoundedBackground()
                    .padding(vertical = 10.dp)
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Design",
                    subTitle = "Compose",
                    icon = R.drawable.ic_compose_logo,
                    percent = 0.9f,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .adbRoundedBackground()
                    .padding(vertical = 10.dp)
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Firebase",
                    subTitle = "Cloud",
                    icon = R.drawable.ic_firebase,
                    percent = 0.7f,
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .adbRoundedBackground()
                    .padding(vertical = 10.dp)
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Version Control",
                    subTitle = "Git",
                    icon = R.drawable.ic_git,
                    percent = 0.8f,
                )
            }
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
