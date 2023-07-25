package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalFoundationApi::class)
data class PagerModel constructor(
    val pagerList: List<PageModel>,
    val pagerState: PagerState,
) {
    data class PageModel(
        val title: String = "",
        val tabIcon: ImageVector,
        val composableScreen: @Composable () -> Unit
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppPager(
    pagerModel: PagerModel,
    userScroll: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerModel.pagerState,
            pageCount = pagerModel.pagerList.size,
            userScrollEnabled = userScroll
        ) { position ->
            pagerModel.pagerList[position].composableScreen()
        }
    }
}
