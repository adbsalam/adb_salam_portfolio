package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.adbsalam.portfolio.navigation.navigateToHome
import uk.adbsalam.portfolio.navigation.navigateToInfo
import uk.adbsalam.portfolio.navigation.navigateToReviews
import uk.adbsalam.portfolio.navigation.navigateToVideos

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
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                label = { Text(text = item.title) },
                selected = selected.value == index,
                onClick = {
                    when (index) {
                        0 -> navController.navigateToHome()
                        1 -> navController.navigateToInfo()
                        2 -> navController.navigateToVideos()
                        3 -> navController.navigateToReviews()
                    }
                    selected.value = index
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
            )
        }
    }
}
