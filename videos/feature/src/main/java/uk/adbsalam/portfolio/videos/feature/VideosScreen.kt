package uk.adbsalam.portfolio.videos.feature

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.VideoData
import uk.adbsalam.portfolio.videos.feature.components.VideoCard

@Composable
fun VideosScreen(
    videos: List<VideoData>,
    currentTheme: Theme
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_main),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Text(
                text = "@adb_salam",
                style = MaterialTheme.typography.titleMedium
            )

            val icon =
                if (currentTheme == Theme.DARK || isSystemInDarkTheme()) R.drawable.ic_youtube_dark else R.drawable.ic_youtube_light

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            videos.forEach { item ->
                VideoCard(videoData = item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview
@Composable
fun VideosPreviewLight() {
    Adb_Theme {
        VideosScreen(
            videos = VideoData.createMock(),
            currentTheme = Theme.LIGHT
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideosPreviewDark() {
    Adb_Theme(true) {
        VideosScreen(
            videos = VideoData.createMock(),
            currentTheme = Theme.DARK
        )
    }
}

