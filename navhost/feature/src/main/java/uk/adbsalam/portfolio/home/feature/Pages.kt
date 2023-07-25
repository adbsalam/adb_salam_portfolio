package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import uk.adbsalam.portfolio.utils.Theme

internal fun createPageList(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
): List<PagerModel.PageModel> {
    return listOf(
        PagerModel.PageModel(
            title = "Home",
            tabIcon = Icons.Outlined.Home
        ) {
            HomeScreen(
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