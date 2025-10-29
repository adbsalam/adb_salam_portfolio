package uk.adbsalam.portfolio.samples.feature.tabLayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
internal fun StickyTabItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onSizeChanged: (DpSize) -> Unit,
) {
    val density = LocalDensity.current
    Box(
        modifier =
            Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onClick() },
                )
                .onSizeChanged { size ->
                    val sizeInDp =
                        with(density) {
                            DpSize(size.width.toDp(), size.height.toDp())
                        }
                    onSizeChanged(sizeInDp)
                }
                .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }
}
