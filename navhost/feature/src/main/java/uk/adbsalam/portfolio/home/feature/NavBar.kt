package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/**
 * @param pagerModel Pager model to setup home pager
 * This will create Navigation Bar buttons for each page
 * Set scroll actions to pager on nav bar buttons
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBottomNavBar(
    pagerModel: PagerModel
) {
    val scope = rememberCoroutineScope()
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        pagerModel.pagerList.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                label = { Text(text = item.title) },
                selected = pagerModel.pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerModel.pagerState.scrollToPage(index)

                    }
                },
                icon = {
                    Icon(
                        imageVector = item.tabIcon,
                        contentDescription = item.title,
                    )
                },
            )
        }
    }
}