package uk.adbsalam.portfolio.gallery.feature

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.adbsalam.portfolio.components.imageCarousal.ImageCarousal
import uk.adbsalam.portfolio.gallery.feature.components.GalleryGrid
import uk.adbsalam.portfolio.gallery.feature.components.MIN_SIZE
import uk.adbsalam.portfolio.navigation.FullScreenArgs
import uk.adbsalam.portfolio.navigation.navigateToFullScreenGallery
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
fun Gallery(
    navController: NavController
) {
    val isTransforming = remember { mutableStateOf(false) }
    val gridCellSize = remember { mutableStateOf(MIN_SIZE) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseSurface),
        userScrollEnabled = !isTransforming.value
    ) {

        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(start = 20.dp, end = 20.dp, top = 14.dp)
            ) {
                Text(
                    text = "Transformable Gallery",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Try this transformable gallery. Pinch or zoom to change grid size of gallery. Click on an image to open in immersive mode with transforming gestures",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            GalleryCarousal(
                title = "Most Recent",
                modifier = Modifier.padding(top = 14.dp),
                imageRes = recent
            )
        }

        item {
            GalleryCarousal(
                modifier = Modifier.padding(top = 20.dp),
                title = "Highlights",
                isHero = true,
                imageRes = highlights
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            GalleryGrid(
                gridCellSize = gridCellSize,
                galleryMedia = GalleryMedia.mockkGallery(),
                onTransforming = { isTransforming.value = it },
                onClick = { title, index ->
                    navController.navigateToFullScreenGallery(
                        FullScreenArgs(
                            title = title,
                            index = index
                        )
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@PreviewLight
@SnapIt
@Composable
internal fun GalleryLightPreview() {
    Adb_Screen_Theme {
        Gallery(rememberNavController())
    }
}


@PreviewDark
@SnapIt(isDark = true)
@Composable
internal fun GalleryDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        Gallery(rememberNavController())
    }
}

@Composable
private fun GalleryCarousal(
    title: String,
    modifier: Modifier = Modifier,
    isHero: Boolean = false,
    @DrawableRes imageRes: List<Int>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 10.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        ImageCarousal(
            imageResList = imageRes,
            isHero = isHero
        )
    }
}