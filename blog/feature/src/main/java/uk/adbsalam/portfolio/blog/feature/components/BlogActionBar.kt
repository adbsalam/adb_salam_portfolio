package uk.adbsalam.portfolio.blog.feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.adbRoundedBackgroundForButtons

@Composable
fun BlogActionsBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .adbRoundedBackgroundForButtons()
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Rounded.Favorite,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Rounded.Share,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Rounded.CopyAll,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@PreviewLight
@Composable
fun BlogActionsBarLightPreview() {
    Adb_Theme {
        BlogActionsBar(Modifier)
    }
}


@PreviewDark
@Composable
fun BlogActionsBarDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        BlogActionsBar(Modifier)
    }
}