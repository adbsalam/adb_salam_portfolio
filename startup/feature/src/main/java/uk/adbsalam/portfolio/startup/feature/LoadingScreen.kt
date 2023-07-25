package uk.adbsalam.portfolio.startup.feature

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.images.AdbMainLogo
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
internal fun LoadingScreen() {

    val animate = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = null) {
        animate.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = animate.value,
            enter = fadeIn(tween(1000))
        ) {
            AdbMainLogo(
                modifier = Modifier
                    .size(150.dp)
            )
        }

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .size(60.dp),
            strokeWidth = 6.dp
        )
    }
}

@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@SnapIt(name = "LoadingScreen - should render correctly")
fun LoadingScreenPreview() {
    Adb_Theme {
        LoadingScreen()
    }
}