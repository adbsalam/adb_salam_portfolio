package uk.adbsalam.portfolio.info.feature.components.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.feature.WorkHistory
import uk.adbsalam.portfolio.info.feature.components.timeline.models.CircleParametersDefaults
import uk.adbsalam.portfolio.info.feature.components.timeline.models.LineParametersDefaults
import uk.adbsalam.portfolio.info.feature.components.timeline.models.StrokeParameters
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
internal fun Timeline() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val mockData = WorkHistory.createMock()
        mockData.forEachIndexed { index, workHistory ->


            println("---------------------------------------" + mockData.lastIndex)
            println("---------------------------------------" + index)

            val stage = when (index) {
                0 -> TimelineNodePosition.FIRST
                mockData.lastIndex -> TimelineNodePosition.LAST
                else -> TimelineNodePosition.MIDDLE
            }

            TimelineNode(
                position = stage,
                circleParameters = CircleParametersDefaults.circleParameters(
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    stroke = StrokeParameters(
                        color = MaterialTheme.colorScheme.primary,
                        width = 5.dp
                    ),
                ),
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = MaterialTheme.colorScheme.primary,
                    endColor = MaterialTheme.colorScheme.primary
                )
            ) { modifier -> TimeLineCard(workHistory, modifier) }
        }
    }
}

@Preview
@Composable
internal fun TimelineLightPreview() {
    Adb_Theme() {
        Timeline()
    }
}