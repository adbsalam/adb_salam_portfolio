package uk.adbsalam.portfolio.theming

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.adbRoundedBackground() = composed { this.background(color = MaterialTheme.colorScheme.background, roundedContainerShape) }