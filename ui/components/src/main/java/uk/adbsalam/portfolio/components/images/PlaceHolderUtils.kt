package uk.adbsalam.portfolio.components.images

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import uk.adbsalam.portfolio.components.R

@DrawableRes
@Composable
internal fun placeholderDrawableResId() = if (LocalInspectionMode.current) R.drawable.preview else 0
