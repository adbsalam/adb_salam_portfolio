package uk.adbsalam.portfolio.samples.feature.tabLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.samples.feature.tabLayout.StickyTabDefaults.StickyTabContentPaddingHorizontal
import uk.adbsalam.portfolio.theming.PreviewDark

@Composable
internal fun StickyTabIndicator(
    width: Dp,
    height: Dp,
    offset: Dp,
    scale: Float,
    style: StickyTabStyle,
) {
    Row(
        Modifier
            .fillMaxWidth(),
    ) {
        when (style) {
            StickyTabStyle.Simple -> {
                SimpleStickyTabIndicator(
                    modifier =
                        Modifier
                            .size(width = width, height = height)
                            .offset(x = offset)
                            .scale(scaleX = scale, scaleY = 1f),
                )
            }

            StickyTabStyle.Card ->
                CardStickyTabIndicator(
                    modifier =
                        Modifier
                            .size(width = width, height = height)
                            .offset(x = offset)
                            .scale(scaleX = scale, scaleY = 1f),
                )
        }
    }
}

@Composable
internal fun SimpleStickyTabIndicator(modifier: Modifier) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = StickyTabContentPaddingHorizontal),
    ) {
        Box(
            modifier =
                modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(30.dp),
                    ),
        )
    }
}

@Composable
internal fun CardStickyTabIndicator(modifier: Modifier) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = StickyTabContentPaddingHorizontal),
    ) {
        Card(
            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
            modifier =
                modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(30.dp),
                    ),
        ) {}
    }
}

@PreviewDark
@Composable
private fun StickyTabIndicatorSimplePreview() {
    StickyTabIndicator(
        width = 300.dp,
        height = 52.dp,
        offset = 0.dp,
        scale = 1f,
        style = StickyTabStyle.Simple,
    )
}

@PreviewDark
@Composable
private fun StickyTabIndicatorCardPreview() {
    StickyTabIndicator(
        width = 300.dp,
        height = 52.dp,
        offset = 0.dp,
        scale = 1f,
        style = StickyTabStyle.Card,
    )
}
