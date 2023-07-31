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

/**
 * @param pagerModel Pager model to be used with a list of pages
 * @param userScroll allow user scroll for true, else disable horizontal scroll
 *
 * This handles horizontal scroll on pager to go to next page,
 * by default set to false
 */
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

/**
 * create pages list to show in pager
 */
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
