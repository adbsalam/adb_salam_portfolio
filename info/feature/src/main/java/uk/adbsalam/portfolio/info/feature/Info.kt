package uk.adbsalam.portfolio.info.feature

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Preview
@Composable
fun InfoGraphics() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

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

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Skills Info Graphics",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalChart(
            subTitle = "Coroutines",
            percent = 1f
        )

        HorizontalChart(
            subTitle = "CI/CD",
            percent = 0.9f
        )

        HorizontalChart(
            subTitle = "REST",
            percent = 1f
        )

        HorizontalChart(
            subTitle = "Unit Test",
            percent = 1f
        )

        HorizontalChart(
            subTitle = "E2E Testing",
            percent = 0.8f
        )

    }
}
