package uk.adbsalam.portfolio.home.feature

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun HomeScreen(
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
){
    Text(text = "Hello Home")
}