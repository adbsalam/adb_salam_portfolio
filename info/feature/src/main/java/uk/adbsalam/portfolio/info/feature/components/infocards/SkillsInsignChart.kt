package uk.adbsalam.portfolio.info.feature.components.infocards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.data.infoList
import uk.adbsalam.portfolio.info.feature.components.InfoTitle
import uk.adbsalam.portfolio.info.feature.components.charts.HorizontalChart
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Top level chart to show Horizontal Chart
 * Animated progress will be shown
 */
@Composable
internal fun SkillsInsightCard() {

    var expandInfoBars by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .adbRoundedBackground()
            .padding(14.dp)
    ) {

        InfoTitle(
            title = "Skills Insight"
        )

        infoList.subList(0, 5).forEach {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalChart(
                subTitle = it.name,
                percent = it.value
            )
        }

        AnimatedVisibility(
            visible = expandInfoBars,
        ) {
            Column(Modifier.fillMaxWidth()) {
                infoList.subList(5, infoList.size).forEach {
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalChart(
                        subTitle = it.name,
                        percent = it.value
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { expandInfoBars = !expandInfoBars }
        ) {
            Text(text = if (expandInfoBars) "see less" else "see more")
        }
    }
}

@PreviewLight
@Composable
@SnapIt(name = "SkillsInsightCard - light mode")
internal fun SkillsInsightCardLight() {
    Adb_Theme {
        SkillsInsightCard()
    }
}

@PreviewDark
@Composable
@SnapIt(name = "SkillsInsightCard - dark mode", isDark = true)
internal fun SkillsInsightCardDark() {
    Adb_Theme(isSystemDark = true) {
        SkillsInsightCard()
    }
}