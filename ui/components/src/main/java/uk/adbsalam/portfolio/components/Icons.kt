package uk.adbsalam.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt
@Composable
fun SettingsIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(60.dp)
            .padding(6.dp)
            .clickable { onClick() }
            .background(
                Color.DarkGray.copy(alpha = 0.9f),
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = Color.White,
        )
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun SettingsIconPreview() {
    SettingsIcon(
        onClick = {}
    )
}