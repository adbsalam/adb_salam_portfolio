package uk.adbsalam.portfolio.home.feature.navbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun HomeBottomNavBar(modifier: Modifier = Modifier) {
    val selected = remember { mutableStateOf(BottomNavItem.HOME) }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        BottomNavItem.values().forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                label = { Text(text = item.title) },
                selected = selected.value == item,
                onClick = { selected.value = item },
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