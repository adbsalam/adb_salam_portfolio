package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import uk.adbsalam.nbrown.home.feature.NBrownHome
import uk.adbsalam.portfolio.info.feature.InfoScreen
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
            NBrownHome(
                onTheme = onTheme,
                onDynamicColor = onDynamicColor
            )
        },
        PagerModel.PageModel(
            title = "Search",
            tabIcon = Icons.Outlined.Search
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Videos")
            }
        },
        PagerModel.PageModel(
            title = "Shop",
            tabIcon = Icons.Outlined.Shop
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Videos")
            }
        },
        PagerModel.PageModel(
            title = "Saved",
            tabIcon = Icons.Outlined.HeartBroken
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Reviews")
            }
        },
        PagerModel.PageModel(
            title = "Bag",
            tabIcon = Icons.Outlined.ShoppingBag
        ) {
            InfoScreen()
        }
    )
}
