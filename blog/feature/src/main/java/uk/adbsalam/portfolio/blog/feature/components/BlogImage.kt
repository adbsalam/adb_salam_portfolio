package uk.adbsalam.portfolio.blog.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import uk.adbsalam.portfolio.components.R

@Composable
fun BlogImage(name: String) {
    val image = painterResource(id = getImage(name = name))
    Image(painter = image, contentDescription = null)
}

@Composable
@DrawableRes
private fun getImage(name: String): Int {
    return when (name) {
        "ic_component" -> R.drawable.ic_component
        "ic_screen" -> R.drawable.ic_screen
        else -> 0
    }
}