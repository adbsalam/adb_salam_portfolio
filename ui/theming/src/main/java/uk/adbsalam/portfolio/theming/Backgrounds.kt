package uk.adbsalam.portfolio.theming

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip

fun Modifier.adbRoundedBackground() = composed {
    this
        .background(color = MaterialTheme.colorScheme.background, roundedContainerShape)
        .clip(roundedContainerShape)
}

fun Modifier.adbRoundedBackgroundForButtons() = composed {
    this
        .background(color = MaterialTheme.colorScheme.primary, roundedContainerShape)
        .clip(roundedContainerShape)
}