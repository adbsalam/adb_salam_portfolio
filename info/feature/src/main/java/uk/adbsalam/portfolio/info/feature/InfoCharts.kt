package uk.adbsalam.portfolio.info.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.info.feature.components.CircularChart
import uk.adbsalam.portfolio.info.feature.components.HorizontalChart

@Preview
@Composable
fun InfoCharts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .animateContentSize(),
    ) {

        var expandInfoBars by remember { mutableStateOf(false) }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Skills Info Graphics",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Android",
                subTitle = "Kotlin",
                percent = 1f,
            )

            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Design",
                subTitle = "Compose",
                percent = 0.9f,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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