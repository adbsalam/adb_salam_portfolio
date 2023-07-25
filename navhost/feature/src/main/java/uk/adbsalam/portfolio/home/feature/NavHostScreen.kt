package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.adbsalam.portfolio.utils.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavHostScreen(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val pagerState = rememberPagerState()

    val pages = createPageList(
        onTheme = onTheme,
        onDynamicColor = onDynamicColor
    )

    val pagerModel = PagerModel(
        pagerState = pagerState,
        pagerList = pages,
    )


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
            AppPager(pagerModel)
        }
    }

}
