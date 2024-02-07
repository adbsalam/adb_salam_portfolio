package uk.adbsalam.portfolio.home.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun HeaderIcon(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .padding(6.dp)
                .clickable { onClick() }
                .background(
                    Color.DarkGray.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(50.dp)
                )
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = icon,
                tint = Color.White,
                contentDescription = null
            )
        }

        Text(text = title, color = Color.White, fontSize = 11.sp)
    }
}

@Preview
@Composable
internal fun HeaderIconPreview() {
    HeaderIcon(
        icon = Icons.Default.AcUnit,
        title = "Get Test",
        onClick = {}
    )
}