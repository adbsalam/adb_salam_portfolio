package uk.adbsalam.portfolio.videos.feature

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
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R.drawable.ic_logo_main
import uk.adbsalam.portfolio.components.R.drawable.ic_youtube_dark
import uk.adbsalam.portfolio.components.R.drawable.ic_youtube_light
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.utils.Theme.DARK
import uk.adbsalam.portfolio.utils.Theme.LIGHT
import uk.adbsalam.portfolio.videos.data.objects.VideoItems
import uk.adbsalam.portfolio.videos.feature.components.VideoCard
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param videos videos to show on this screen
 * @param currentTheme theme app is currently using
 *
 * This is the main UI Screen to show all videos on
 */
@Composable
internal fun VideosScreen(
    videos: VideoItems,
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
                painter = painterResource(id = ic_logo_main),
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
                if (currentTheme == DARK || isSystemInDarkTheme()) ic_youtube_dark else ic_youtube_light

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
            videos.videos.forEach { item ->
                VideoCard(videoData = item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@PreviewLight
@Composable
@SnapIt(name = "VideosScreen - light mode")
internal fun VideosScreenPreviewLight() {
    Adb_Screen_Theme {
        VideosScreen(
            videos = VideoItems.createMock(),
            currentTheme = LIGHT
        )
    }
}

@PreviewDark
@Composable
@SnapIt(name = "VideosScreen - dark mode", isDark = true)
internal fun VideosScreenPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        VideosScreen(
            videos = VideoItems.createMock(),
            currentTheme = DARK
        )
    }
}

