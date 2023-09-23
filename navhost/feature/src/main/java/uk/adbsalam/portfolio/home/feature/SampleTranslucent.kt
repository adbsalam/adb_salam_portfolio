package uk.adbsalam.portfolio.home.feature

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
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.theming.light_gradient_color_two

@Composable
fun Cover(
    currentIndex: Int = 0,
    startHere: MutableState<Float>,
    duration: MutableState<Float> = remember { mutableStateOf(1000f) },
    threshold: MutableState<Float> = remember { mutableStateOf(0.97f) }
) {


    var componentWidth by remember { mutableStateOf(0f) }

    var animateTwo by remember { mutableFloatStateOf(1f) }
    var animateThree by remember { mutableFloatStateOf(1f) }
    var animateFour by remember { mutableFloatStateOf(1f) }
    var animateFive by remember { mutableFloatStateOf(1f) }

    var twoReset by rememberSaveable { mutableStateOf(false) }
    var threeReset by rememberSaveable { mutableStateOf(false) }
    var fourReset by rememberSaveable { mutableStateOf(false) }
    var fiveReset by rememberSaveable { mutableStateOf(false) }

    var manualReset by remember { mutableStateOf(false) }

    val animateOneAlpha by animateFloatAsState(
        targetValue = startHere.value,
        label = "",
        animationSpec = tween(duration.value.toInt()),
    )

    val animateTwoAlpha by animateFloatAsState(
        targetValue = animateTwo,
        label = "",
        animationSpec = tween(duration.value.toInt())
    )

    val animateThreeAlpha by animateFloatAsState(
        targetValue = animateThree,
        label = "",
        animationSpec = tween(duration.value.toInt()),
    )

    val animateFourAlpha by animateFloatAsState(
        targetValue = animateFour,
        label = "",
        animationSpec = tween(duration.value.toInt()),
    )

    val animateFiveAlpha by animateFloatAsState(
        targetValue = animateFive,
        label = "",
        animationSpec = tween(duration.value.toInt()),
    )

    if (animateOneAlpha < threshold.value && !twoReset && !manualReset) {
        animateTwo = 0f
        twoReset = true
    }

    if (animateTwoAlpha < threshold.value && !threeReset && !manualReset) {
        animateThree = 0f
        threeReset = true
    }

    if (animateThreeAlpha < threshold.value && !fourReset && !manualReset) {
        animateFour = 0f
        fourReset = true
    }

    if (animateFour < threshold.value && !fiveReset && !manualReset) {
        animateFive = 0f
        fiveReset = true
    }


    val background = if(currentIndex == 0) {
        Brush.radialGradient(
            0.0F to light_gradient_color_two.copy(animateOneAlpha),
            0.4F to light_gradient_color_two.copy(animateTwoAlpha),
            0.6F to light_gradient_color_two.copy(animateThreeAlpha),
            1F to light_gradient_color_two.copy(animateThreeAlpha),
            center = Offset(0f, 100f),
            radius = 900f,
            tileMode = TileMode.Decal
        )
    }
    else{
        Brush.radialGradient(
            0.0F to light_gradient_color_two.copy(animateOneAlpha),
            0.4F to light_gradient_color_two.copy(animateTwoAlpha),
            0.6F to light_gradient_color_two.copy(animateThreeAlpha),
            1F to light_gradient_color_two.copy(animateThreeAlpha),
            center = Offset(-100f, 200f),
            radius = 900f,
            tileMode = TileMode.Decal
        )
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
                        .onSizeChanged {
                            componentWidth = it.width.toFloat()
                        }
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
                                    onTap = {
                                        if (startHere.value == 0f) {
                                            manualReset = true
                                            startHere.value = 1f
                                            animateTwo = 1f
                                            animateThree = 1f
                                            animateFour = 1f
                                            animateFive = 1f

                                            twoReset = false
                                            threeReset = false
                                            fourReset = false
                                            fiveReset = false

                                        } else {
                                            manualReset = false
                                            startHere.value = 0f
                                        }
                                    }
                                )
                            }
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(background)
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
                                0.25F to Color.White.copy(animateTwoAlpha),
                                0.5F to Color.White.copy(animateThreeAlpha),
                                0.75F to Color.White.copy(animateFourAlpha),
                                1F to Color.White.copy(animateFiveAlpha),
                            )
                        )
                )
            }
        }
    }
}
