package uk.adbsalam.nbrown.home.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.nbrown.home.feature.components.CarousalItem
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@Composable
fun HomeImage() {

    var animate by remember { mutableStateOf(false) }

    LaunchedEffect(null) {
        animate = true
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .adbRoundedBackground()
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(238.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AnimatedVisibility(
                visible = animate,
                enter = fadeIn(tween(1000))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "It's giving main character energy",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "It's giving main character energy",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    TextButton(onClick = {}) {
                        Text(text = "Shop brands")
                    }
                }
            }


            AnimatedVisibility(
                visible = animate,
                enter = fadeIn(tween(1000)) + expandIn(tween(1000))
            ) {
                Divider(
                    Modifier
                        .width(100.dp)
                        .height(2.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_home_top),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(
                MaterialTheme.colorScheme.background,
                blendMode = BlendMode.DstIn
            ),
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
        )
    }


}

@Preview
@Composable
fun HomeImagePreview() {
    HomeImage()
}