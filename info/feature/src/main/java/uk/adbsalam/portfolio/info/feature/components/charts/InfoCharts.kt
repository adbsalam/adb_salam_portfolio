package uk.adbsalam.portfolio.info.feature.components.charts

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.info.feature.infoList
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.primary_light

@Composable
fun InfoCharts() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .animateContentSize()
            .padding(horizontal = 16.dp)
    ) {

        var expandInfoBars by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp))
                .padding(14.dp),
        ) {

            Text(
                text = "Android Main",
                style = MaterialTheme.typography.titleSmall,
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 20.dp)
            )

            Row(Modifier.fillMaxWidth()) {
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

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp))
                .padding(14.dp)
        ) {

            Text(
                text = "Skills Insight",
                style = MaterialTheme.typography.titleSmall,
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 10.dp)
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

}

@Composable
@Preview
internal fun InfoCartsLightPreview() {
    Adb_Theme {
        InfoCharts()
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
internal fun InfoCartsDarkPreview() {
    Adb_Theme(true) {
        InfoCharts()
    }
}