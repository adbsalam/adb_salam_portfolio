package uk.adbsalam.portfolio.samples.feature.tabLayout.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight

@Composable
internal fun StickyTabAnimationSlider(
    title: String,
    onDampingRatioChange: (Int) -> Unit,
    onStiffnessChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleSmall)
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
        ) {
            AnimationSlider(
                title = title,
                modifier = Modifier.weight(0.5f),
                onValueChange = { onDampingRatioChange(it) },
            )

            AnimationSlider(
                title = "Stiffness",
                modifier = Modifier.weight(0.5f),
                onValueChange = { onStiffnessChange(it) },
            )
        }
    }
}

@Composable
private fun AnimationSlider(
    modifier: Modifier = Modifier,
    title: String = "",
    onValueChange: (Int) -> Unit = {},
) {
    var sliderValue by remember { mutableFloatStateOf(1f) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = "$title: ${sliderValue.toInt()}",
        )

        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                onValueChange(it.toInt())
            },
            valueRange = 1f..6f,
            steps = 5,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

@PreviewLight
@PreviewDark
@Composable
private fun StickyTabAnimationSliderPreview() {
    StickyTabAnimationSlider(
        title = "Title",
        onStiffnessChange = {},
        onDampingRatioChange = {}
    )
}
