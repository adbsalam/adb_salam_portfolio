package uk.adbsalam.portfolio.home.feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.data.SocialMedia
import uk.adbsalam.portfolio.home.feature.utils.startActivityForLink
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme

@Composable
internal fun HomeHeader(
    theme: Theme,
    headerHeight: Dp,
    parallaxTranslation: Float,
    onSettingsClick: () -> Unit,
) {
    Box(modifier = Modifier) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .graphicsLayer { translationY = parallaxTranslation },
        ) {

            Profile(
                theme = theme,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(all = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderIcon(
                    icon = Icons.Default.Share,
                    title = "Share app",
                    onClick = { startActivityForLink(context = context, SocialMedia.G_PLAY) }
                )

                HeaderIcon(
                    icon = Icons.Default.Code,
                    title = "See Git",
                    onClick = { startActivityForLink(context = context, SocialMedia.GIT) }
                )
                HeaderIcon(
                    icon = Icons.Default.PlayArrow,
                    "Videos",
                    onClick = { startActivityForLink(context = context, SocialMedia.YOUTUBE) }
                )
                HeaderIcon(icon = Icons.Default.Settings, "Settings", onClick = onSettingsClick)
            }
        }
    }
}

@Composable
@PreviewLight
internal fun HomeHeaderPreviewLight() {
    Adb_Screen_Theme {
        HomeHeader(
            theme = Theme.LIGHT,
            headerHeight = 600.dp,
            parallaxTranslation = 20f,
            onSettingsClick = { /* unused */ }
        )
    }
}

@Composable
@PreviewLight
internal fun HomeHeaderPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        HomeHeader(
            theme = Theme.DARK,
            headerHeight = 400.dp,
            parallaxTranslation = 0.5f,
            onSettingsClick = { /* unused */ }
        )
    }
}