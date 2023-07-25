package uk.adbsalam.portfolio.home.feature.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String
) {
    HOME("Home", Icons.Default.Home, "home"),
    INFO("Info", Icons.Default.Info, "info"),
    VIDEOS("videos", Icons.Default.Movie, "videos"),
    REVIEWS("reviews", Icons.Default.Forum, "reviews")
}