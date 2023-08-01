package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.feature.components.charts.InfoCharts
import uk.adbsalam.portfolio.info.feature.components.timeline.Timeline
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun InfoGraphics() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        InfoCharts()

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Work History",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Timeline()

        Spacer(modifier = Modifier.height(30.dp))
    }

}

@Preview
@Composable
fun InfoGraphicsPreviewLight() {
    Adb_Theme {
        InfoGraphics()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,)
@Composable
fun InfoGraphicsPreviewDark() {
    Adb_Theme(true) {
        InfoGraphics()
    }
}