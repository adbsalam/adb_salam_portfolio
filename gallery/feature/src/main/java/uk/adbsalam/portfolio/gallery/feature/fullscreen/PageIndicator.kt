package uk.adbsalam.portfolio.gallery.feature.fullscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pageCount: Int,
    indicatorCount: Int = 5,
    indicatorSize: Dp = 8.dp,
    indicatorShape: Shape = CircleShape,
    space: Dp = 4.dp,
) {
    val listState = rememberLazyListState()
    val totalWidth: Dp = indicatorSize * indicatorCount + space * (indicatorCount - 1)
    val widthInPx = LocalDensity.current.run { indicatorSize.toPx() }

    val currentItem by remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }

    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        listState.animateScrollToItem(
            currentItem,
            (widthInPx / 2 - viewportSize.width / 2).toInt()
        )
    }

    LazyRow(
        modifier = modifier.width(totalWidth),
        state = listState,
        contentPadding = PaddingValues(vertical = space),
        horizontalArrangement = Arrangement.spacedBy(space),
        userScrollEnabled = false
    ) {

        items(pageCount) { index ->
            val isSelected = (index == currentItem)

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = if(isSelected) 1f else 0.7f
                        scaleY = if(isSelected) 1f else 0.7f
                    }
                    .clip(indicatorShape)
                    .size(indicatorSize)
                    .background(
                        if (isSelected) Color.White else Color.LightGray,
                        indicatorShape
                    )
            )
        }
    }
}