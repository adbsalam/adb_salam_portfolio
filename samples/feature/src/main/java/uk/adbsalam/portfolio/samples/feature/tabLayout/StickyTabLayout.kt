package uk.adbsalam.portfolio.samples.feature.tabLayout

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.samples.feature.tabLayout.StickyTabDefaults.StickyTabContentPaddingHorizontal
import uk.adbsalam.portfolio.samples.feature.tabLayout.data.tabItems
import uk.adbsalam.portfolio.samples.feature.tabLayout.editor.StickyTabAnimationSlider
import uk.adbsalam.portfolio.samples.feature.tabLayout.editor.TabIndicatorAnimationState
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme

@Composable
fun StickyTabLayout() {
    var animationState by remember { mutableStateOf(TabIndicatorAnimationState.empty) }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 12.dp)
                .statusBarsPadding(),
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Simple Style",
            style = MaterialTheme.typography.titleMedium,
        )
        StickyTabLayoutSample(
            modifier = Modifier.fillMaxWidth(),
            style = StickyTabStyle.Simple,
            animationState = animationState
        )

        Text(
            modifier = Modifier.padding(12.dp),
            text = "Card Style",
            style = MaterialTheme.typography.titleMedium,
        )
        StickyTabLayoutSample(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                    .padding(vertical = 8.dp),
            style = StickyTabStyle.Card,
            animationState = animationState
        )

        StickyTabAnimationSlider(
            title = "Offset",
            onDampingRatioChange = {
                animationState = animationState.copy(offsetAnimationDampingRatio = it)
            },
            onStiffnessChange = {
                animationState = animationState.copy(offsetAnimationStiffness = it)
            }
        )

        StickyTabAnimationSlider(
            title = "Width",
            onDampingRatioChange = {
                animationState = animationState.copy(widthAnimationDampingRatio = it)
            },
            onStiffnessChange = {
                animationState = animationState.copy(widthAnimationStiffness = it)
            }
        )
    }
}

@Composable
private fun StickyTabLayoutSample(
    modifier: Modifier,
    style: StickyTabStyle,
    animationState: TabIndicatorAnimationState
) {
    val sizeHashMap = remember { mutableStateMapOf<Int, DpSize>() }
    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableIntStateOf(0) }
    var sizesRetrieved by remember { mutableStateOf(false) }
    var offsetDp by remember { mutableStateOf(0.dp) }
    var indicatorSize by remember { mutableStateOf(DpSize.Zero) }
    var indicatorScale by remember { mutableFloatStateOf(1f) }
    var selectionDirection by remember { mutableStateOf<SelectionDirection?>(null) }
    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val animateOffSet by animateDpAsState(
        targetValue = offsetDp,
        animationSpec =
            spring(
                dampingRatio = animationState.offsetAnimationDampingRatio.toBounce(),
                stiffness = animationState.offsetAnimationStiffness.toStiffness(),
            ),
    )

    val animateWidth by animateDpAsState(
        targetValue = indicatorSize.width,
        animationSpec =
            spring(
                dampingRatio = animationState.widthAnimationDampingRatio.toBounce(),
                stiffness = animationState.widthAnimationStiffness.toStiffness(),
            ),
    )

    val scaleAnimate by animateFloatAsState(
        targetValue = indicatorScale,
        finishedListener = { indicatorScale = 1f },
    )
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        contentAlignment = Alignment.Center,
    ) {
        LaunchedEffect(selectedIndex) {
            if (sizesRetrieved) {
                indicatorSize = sizeHashMap[selectedIndex] ?: DpSize.Zero
                indicatorScale = 1.3f
            }
        }

        LaunchedEffect(listState, selectedIndex) {
            snapshotFlow {
                listState.layoutInfo.visibleItemsInfo
                    .find { it.key == selectedIndex }
                    ?.offset
            }.distinctUntilChanged().collect { offsetPx ->
                if (offsetPx != null) {
                    offsetDp = with(density) { offsetPx.toDp() }
                }
            }
        }

        LaunchedEffect(selectionDirection) {
            selectionDirection?.let {
                scope.launch {
                    when (it) {
                        SelectionDirection.FORWARD -> {
                            val lastVisibleItemIndex =
                                listState.layoutInfo.visibleItemsInfo.lastIndex
                            val isSecondLast = lastVisibleItemIndex == (selectedIndex + 1)
                            val isLastItem = selectedIndex == lastVisibleItemIndex
                            if (isLastItem || isSecondLast) {
                                val index = (selectedIndex - 1).coerceAtLeast(0)
                                listState.animateScrollToItem(index)
                            }
                        }

                        SelectionDirection.BACKWARD -> {
                            val index = (selectedIndex - 1).coerceAtLeast(0)
                            listState.animateScrollToItem(index)
                        }
                    }

                    selectionDirection = null
                }
            }
        }

        if (indicatorSize != DpSize.Zero) {
            StickyTabIndicator(
                width = animateWidth,
                height = indicatorSize.height,
                offset = animateOffSet,
                scale = scaleAnimate,
                style = style,
            )
        }

        LazyRow(
            modifier = modifier,
            state = listState,
            contentPadding = PaddingValues(horizontal = StickyTabContentPaddingHorizontal)
        ) {
            tabItems.forEachIndexed { index, item ->
                item(key = index) {
                    StickyTabItem(
                        title = item,
                        isSelected = selectedIndex == index,
                        onClick = {
                            selectionDirection =
                                if (index < selectedIndex) SelectionDirection.BACKWARD else SelectionDirection.FORWARD
                            selectedIndex = index
                        },
                        onSizeChanged = {
                            sizesRetrieved = true
                            sizeHashMap[index] = it
                        },
                    )
                }
            }
        }
    }
}

fun Int.toBounce(): Float =
    when {
        this < 2 -> Spring.DampingRatioNoBouncy
        this < 3 -> Spring.DampingRatioLowBouncy
        this < 4 -> Spring.DampingRatioMediumBouncy
        else -> Spring.DampingRatioHighBouncy
    }

fun Int.toStiffness(): Float =
    when {
        this < 2 -> Spring.StiffnessVeryLow
        this < 3 -> Spring.StiffnessLow
        this < 4 -> Spring.StiffnessMediumLow
        this < 5 -> Spring.StiffnessMedium
        else -> Spring.StiffnessHigh
    }

@Preview
@Composable
fun StickyTabLayoutPreview() {
    Adb_Screen_Theme(isDark = true) {
        StickyTabLayout()
    }
}
