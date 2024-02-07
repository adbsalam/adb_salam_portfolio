package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.adbsalam.portfolio.navigation.navigateToHome
import uk.adbsalam.portfolio.navigation.navigateToInfo
import uk.adbsalam.portfolio.navigation.navigateToReviews
import uk.adbsalam.portfolio.navigation.navigateToVideos
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme

/**
 * @param selected selected item on nav bar
 * This will create Navigation Bar buttons for each page
 * Set scroll actions to pager on nav bar buttons
 */
@Composable
fun RootNavBar(
    selected: MutableState<Int>,
    navController: NavHostController
) {
    RootNavBar(
        selected = selected,
        onHomeClick = navController::navigateToHome,
        onInfoClick = navController::navigateToInfo,
        onVideoClick = navController::navigateToVideos,
        onReviewClick = navController::navigateToReviews,
    )
}


@Composable
private fun RootNavBar(
    selected: MutableState<Int>,
    onHomeClick: () -> Unit,
    onInfoClick: () -> Unit,
    onVideoClick: () -> Unit,
    onReviewClick: () -> Unit,
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        navItems.forEachIndexed { index, item ->

            NavigationBarItem(
                alwaysShowLabel = true,
                label = { Text(text = item.title) },
                selected = selected.value == index,
                onClick = {
                    when (index) {
                        0 -> onHomeClick()
                        1 -> onInfoClick()
                        2 -> onVideoClick()
                        3 -> onReviewClick()
                    }
                    selected.value = index
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
            )
        }
    }
}

@Composable
private fun RowScope.NavButton(
    item: NavItem,
    isSelected: Boolean,
    index: Int,
    onSelected: (Int) -> Unit
) {
    val background =
        if (isSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.onBackground

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .weight(1f)
            .clickable { onSelected(index) }
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = background
        )
        Text(text = item.title, fontSize = 11.sp, color = background)
    }
}


@Composable
@PreviewLight
internal fun RooNavBarPreviewLight() {
    Adb_Screen_Theme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RootNavBar(
                selected = remember { mutableIntStateOf(0) },
                onHomeClick = { /* unused */ },
                onInfoClick = { /* unused */ },
                onVideoClick = { /* unused */ },
                onReviewClick = { /* unused */ }
            )
        }
    }
}

@Composable
@PreviewDark
internal fun RooNavBarPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RootNavBar(
                selected = remember { mutableIntStateOf(0) },
                onHomeClick = { /* unused */ },
                onInfoClick = { /* unused */ },
                onVideoClick = { /* unused */ },
                onReviewClick = { /* unused */ }
            )
        }
    }
}

