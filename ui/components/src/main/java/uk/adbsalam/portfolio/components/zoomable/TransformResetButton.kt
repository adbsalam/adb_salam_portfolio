package uk.adbsalam.portfolio.components.zoomable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.enterFadeAndSlide
import uk.adbsalam.portfolio.theming.exitFadeAndSlide

@Composable
fun TransformResetButton(
    modifier: Modifier = Modifier,
    visibility: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visibility,
        enter = enterFadeAndSlide(directionY = 50),
        exit = exitFadeAndSlide(directionY = 50)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.background(shape = RoundedCornerShape(50), color = Color.White)
            ) {
                Icon(
                    modifier = Modifier.padding(6.dp),
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            Text(text = "Reset", color = Color.White)
        }
    }
}