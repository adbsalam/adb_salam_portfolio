package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavBarScreen(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val pagerState = rememberPagerState()
    val visibility = remember { mutableStateOf(false) }

    val pages = createPageList(
        onTheme = onTheme,
        onDynamicColor = onDynamicColor
    )

    val pagerModel = PagerModel(
        pagerState = pagerState,
        pagerList = pages,
    )

    LaunchedEffect(key1 = null) {
        visibility.value = true
    }

    AnimatedVisibility(
        visible = visibility.value,
        enter = fadeIn(tween(500))
    ) {
        Scaffold(
            containerColor = Color.Unspecified,
            bottomBar = {
                HomeBottomNavBar(pagerModel = pagerModel)
            }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize()
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                if (!LocalInspectionMode.current) {
                    NavHostPager(pagerModel)
                }
            }
        }
    }

}

@Preview
@Composable
fun NavBarScreenPreviewLight() {
    Adb_Theme {
        NavBarScreen(
            onTheme = {},
            onDynamicColor = {}
        )
    }
}

@Preview
@Composable
fun NavBarScreenPreviewDark() {
    Adb_Theme(
        isSystemDark = true
    ) {
        NavBarScreen(
            onTheme = {},
            onDynamicColor = {}
        )
    }
}
