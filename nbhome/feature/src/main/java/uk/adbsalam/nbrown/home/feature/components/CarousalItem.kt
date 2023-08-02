package uk.adbsalam.nbrown.home.feature.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun CarousalItem() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        var visibility by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.animateContentSize(spring())
        ) {

            Column(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
                    .clickable {
                        visibility = !visibility
                    }
            ) {

                Image(
                    modifier = Modifier.height(160.dp),
                    painter = painterResource(id = R.drawable.ic_home_top),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = "£32.00",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Cargo pink pants",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
            }

            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(400)) + expandHorizontally(tween(400)),
                exit = fadeOut(tween(400)) + shrinkHorizontally(tween(400)),
            ) {
                Column(
                    modifier = Modifier
                        .height(240.dp)
                        .width(60.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.HeartBroken,
                            contentDescription = null
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
internal fun CarouselLightPreview() {
    Adb_Theme {
        CarousalItem()
    }
}

@Composable
@Preview
internal fun CarouselDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        CarousalItem()
    }
}