package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.feature.components.WorkInfo
import uk.adbsalam.portfolio.info.feature.components.infocards.AndroidMainCard
import uk.adbsalam.portfolio.info.feature.components.infocards.SkillsInsightCard
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@Composable
fun InfoGraphics() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AndroidMainCard()

        Text(
            text = "Have a look at my skill set",
            style = MaterialTheme.typography.titleMedium
        )

        SkillsInsightCard()

        Text(
            text = "My Work History",
            style = MaterialTheme.typography.titleMedium
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .adbRoundedBackground()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            val mock = WorkHistory.createMock()
            mock.forEachIndexed { index, item ->
                WorkInfo(
                    showDivider = index != mock.lastIndex,
                    workHistory = item,
                )
            }
        }

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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoGraphicsPreviewDark() {
    Adb_Theme(true) {
        InfoGraphics()
    }
}