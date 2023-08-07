package uk.adbsalam.portfolio.videos.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.videos.feature.components.Player
import uk.adbsalam.portfolio.videos.feature.components.VideoData

@Preview(showSystemUi = true)
@Composable
fun Videos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        val config = LocalConfiguration.current

        Column(Modifier.height(200.dp)) {

        }

        VideoData.createMock().forEachIndexed { index, item ->
            Player(videoData = item, index = index, play = false)
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}