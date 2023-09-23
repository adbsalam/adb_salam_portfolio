package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.light_gradient_color_two

@Composable
fun Cover() {

    var animateOne by remember { mutableFloatStateOf(1f) }
    var animateTwo by remember { mutableFloatStateOf(1f) }
    var animateThree by remember { mutableFloatStateOf(1f) }

    var twoReset by rememberSaveable { mutableStateOf(false) }
    var threeReset by rememberSaveable { mutableStateOf(false) }

    val animateOneAlpha by animateFloatAsState(
        targetValue = animateOne,
        label = "",
        animationSpec = tween(1000, easing = LinearOutSlowInEasing),
    )

    val animateTwoAlpha by animateFloatAsState(
        targetValue = animateTwo,
        label = "",
        animationSpec = tween(1000)
    )

    val animateThreeAlpha by animateFloatAsState(
        targetValue = animateThree,
        label = "",
        animationSpec = tween(1000),
    )

    if (animateOneAlpha < 0.8 && !twoReset) {
        animateTwo = 0f
        twoReset = true
    }

    if (animateTwoAlpha < 0.8 && !threeReset) {
        animateThree = 0f
        threeReset = true
    }

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.size(240.dp, 300.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = uk.adbsalam.portfolio.components.R.drawable.swiss_8),
                        contentDescription = null
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {  animateOne = if (animateOne == 0f) 1f else 0f}
                                )
                            }
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(
                                Brush.linearGradient(
                                    0F to light_gradient_color_two.copy(animateOneAlpha),
                                    0.5F to light_gradient_color_two.copy(animateTwoAlpha),
                                    1F to light_gradient_color_two.copy(animateThreeAlpha),
                                    start = Offset(0f, 0f)
                                )
                            )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "This is some sample text here, this is soo cool!!",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                0F to Color.White.copy(animateOneAlpha),
                                0.5F to Color.White.copy(animateTwoAlpha),
                                1F to Color.White.copy(animateThreeAlpha),
                            )
                        )
                )
            }
        }
    }
}
