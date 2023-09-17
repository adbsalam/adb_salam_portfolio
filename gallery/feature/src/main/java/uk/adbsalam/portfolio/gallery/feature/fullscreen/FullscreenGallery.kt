package uk.adbsalam.portfolio.gallery.feature.fullscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.components.zoomable.transformer
import uk.adbsalam.portfolio.navigation.FullScreenArgs
import uk.adbsalam.portfolio.navigation.navArgs
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.theming.enterFadeAndSlide
import uk.adbsalam.portfolio.theming.exitFadeAndSlide
import uk.adbsalam.snapit.annotations.SnapIt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FullscreenGallery(
    navController: NavController
) {
    val contentSize = remember { mutableStateOf(IntSize(0, 0)) }
    val resetTransform = remember { mutableStateOf(false) }
    var isTransforming by remember { mutableStateOf(false) }
    var showControls by remember { mutableStateOf(true) }

    val fullScreenArgs = navController.navArgs() ?: FullScreenArgs()
    val media = FullScreenMedia.mockkGallery().firstOrNull() { it.title == fullScreenArgs.title }
        ?: FullScreenMedia.mockkGallery().first()

    val pagerState = rememberPagerState(
        initialPage = fullScreenArgs.index,
        initialPageOffsetFraction = 0f,
        pageCount = { media.images.size }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .transformer(
                    resetTransformation = resetTransform,
                    contentSize = contentSize,
                    onTap = { showControls = !showControls },
                    onTransForming = { isTransforming = it }
                ),
            state = pagerState,
            pageSpacing = 12.dp,
            userScrollEnabled = !isTransforming,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 2,
            pageContent = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .aspectRatio(4f / 3f)
                            .onSizeChanged { contentSize.value = it }
                            .wrapContentHeight()
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = media.images[it]),
                        contentDescription = null
                    )
                }
            }
        )

        AnimatedVisibility(
            visible = showControls,
            enter = enterFadeAndSlide(-50),
            exit = exitFadeAndSlide(-50),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.weight(1f),
                    text = media.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                OutlinedButton(
                    onClick = { navController.navigateUp() },
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Black),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(40.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
                ) {
                    Icon(Icons.Default.Clear, contentDescription = "content description")
                }
            }
        }

        AnimatedVisibility(
            visible = showControls,
            enter = enterFadeAndSlide(50),
            exit = exitFadeAndSlide(50),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
        ) {
            PagerIndicator(
                pagerState = pagerState,
                pageCount = media.images.size
            )
        }
    }
}

@PreviewLight
@SnapIt
@Composable
internal fun FullscreenGalleryLightPreview() {
    Adb_Screen_Theme {
        FullscreenGallery(rememberNavController())
    }
}

@PreviewDark
@Composable
internal fun FullscreenGalleryDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        FullscreenGallery(rememberNavController())
    }
}