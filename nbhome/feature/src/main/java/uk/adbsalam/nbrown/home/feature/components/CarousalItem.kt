package uk.adbsalam.nbrown.home.feature.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material.icons.filled.Share
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
import uk.adbsalam.nbrown.home.feature.data.Product
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarousalItem(
    product: Product
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        var visibility by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.animateContentSize(spring())
        ) {

            Column(
                modifier = Modifier
                    .width(160.dp)
                    .height(280.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = { visibility = !visibility }
                    )
            ) {

                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .width(160.dp),
                    painter = painterResource(id = product.image),
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
                        text = product.price,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
            }

            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(500)) + expandHorizontally(tween(500)),
                exit = fadeOut(tween(500)) + shrinkHorizontally(tween(500)),
            ) {
                Column(
                    modifier = Modifier
                        .height(280.dp)
                        .width(60.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.HeartBroken,
                            contentDescription = null
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = null
                        )
                        Icon(
                            imageVector = Icons.Default.Share,
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
        CarousalItem(Product.createData().first())
    }
}

@Composable
@Preview
internal fun CarouselDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        CarousalItem(Product.createData().first())
    }
}