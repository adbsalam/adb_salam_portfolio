package uk.adbsalam.portfolio.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
fun SettingsIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Default.Settings,
        contentDescription = "Settings",
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() }
            .then(modifier)
    )
}

@PreviewLight
@Composable
@SnapIt("SettingsIcon - settings icon")
internal fun SettingsIconPreview() {
    SettingsIcon(
        onClick = {}
    )
}