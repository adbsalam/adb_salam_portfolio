package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import uk.adbsalam.portfolio.utils.Theme

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
fun NavHostPager(
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

internal fun createPageList(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
): List<PagerModel.PageModel> {
    return listOf(
        PagerModel.PageModel(
            title = "Home",
            tabIcon = Icons.Outlined.Home
        ) {
            Homepage(
                onTheme = onTheme,
                onDynamicColor = onDynamicColor
            )
        },
        PagerModel.PageModel(
            title = "Info",
            tabIcon = Icons.Outlined.Info
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Info")
            }
        },
        PagerModel.PageModel(
            title = "Videos",
            tabIcon = Icons.Outlined.Movie
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Videos")
            }
        },
        PagerModel.PageModel(
            title = "Reviews",
            tabIcon = Icons.Outlined.Forum
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Reviews")
            }
        }
    )
}
