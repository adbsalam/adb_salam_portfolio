package uk.adbsalam.portfolio.home.feature

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val title: String,
    val icon: ImageVector,
    val index: Int
)

val navItems = listOf(
    NavItem(
        title = "Home",
        icon = Icons.Outlined.Home,
        index = 0
    ),
    NavItem(
        title = "Info",
        icon = Icons.Outlined.Info,
        index = 0
    ),
    NavItem(
        title = "Videos",
        icon = Icons.Outlined.Movie,
        index = 0
    ),
    NavItem(
        title = "Reviews",
        icon = Icons.Outlined.Forum,
        index = 0
    ),
)