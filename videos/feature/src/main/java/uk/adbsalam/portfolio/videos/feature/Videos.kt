package uk.adbsalam.portfolio.videos.feature

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import uk.adbsalam.portfolio.videos.feature.components.Player
import uk.adbsalam.portfolio.videos.feature.components.VideoData

@Preview(showSystemUi = true)
@Composable
fun Videos() {

    val currentPlaying by remember { mutableStateOf(0) }
    val state = rememberScrollState()
    var currenty by remember { mutableStateOf(0f) }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawLine(
                    brush = Brush.horizontalGradient(listOf(Color.Red, Color.Green)),
                    start = Offset(0f, 1300f),
                    end = Offset(0f, 0f),
                    strokeWidth = 20f
                )
            }
            .statusBarsPadding()
            .verticalScroll(state),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(Modifier.height(200.dp)) {

        }

        VideoData.createMock().forEachIndexed { index, item ->

            Player(
                videoData = item,
                index = index,
                play = currentPlaying == index,
                onPosition = { y ->
                    currenty = y
                    if (index == 0) {
                        println("--------------------------     " + y)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}