package uk.adbsalam.portfolio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AnimatedColumn(
    content: @Composable () -> Unit
) {
    var visibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = null) {
        visibility = true
    }

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(tween(1000))
    ) {
        content()
    }
}