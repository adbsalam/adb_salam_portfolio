package uk.adbsalam.portfolio.samples.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShimmerCardSample(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val manualReset = rememberSaveable { mutableStateOf(false) }

        val duration = rememberSaveable { mutableFloatStateOf(500f) }
        val threshold = rememberSaveable { mutableFloatStateOf(0.97f) }

        val startOne = rememberSaveable { mutableFloatStateOf(1f) }
        val startTwo = rememberSaveable { mutableFloatStateOf(1f) }

        val startThree = rememberSaveable { mutableFloatStateOf(1f) }
        val startFour = rememberSaveable { mutableFloatStateOf(1f) }
        val scope = rememberCoroutineScope()

        Text(
            text = "Try out Synchronised Shimmer",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(40.dp))

        LaunchedEffect(
            key1 = null,
            block = {
                delay(1000)
                startOne.floatValue = 0f

                delay(150)
                startTwo.floatValue = 0f
                startThree.floatValue = 0f

                delay(20)
                startFour.floatValue = 0f
            }
        )

        Row() {
            Cover(
                threshold = threshold,
                duration = duration,
                startHere = startOne,
                manualReset = manualReset
            )
            Spacer(modifier = Modifier.width(12.dp))
            Cover(
                threshold = threshold,
                duration = duration,
                currentIndex = 1,
                startHere = startTwo,
                manualReset = manualReset
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row() {
            Cover(
                threshold = threshold,
                duration = duration,
                startHere = startThree,
                currentIndex = 2,
                manualReset = manualReset
            )
            Spacer(modifier = Modifier.width(12.dp))
            Cover(
                threshold = threshold,
                duration = duration,
                currentIndex = 3,
                startHere = startFour,
                manualReset = manualReset
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(22.dp))

            Slider(
                value = duration.floatValue,
                onValueChange = { duration.floatValue = it },
                valueRange = 0f..1000f
            )
            Text(text = "Duration: " + duration.floatValue.toString())
        }

        Spacer(Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    manualReset.value = true
                    startOne.floatValue = 1f
                    startTwo.floatValue = 1f
                    startThree.floatValue = 1f
                    startFour.floatValue = 1f

                }) {
                Text(text = "reset")
            }

            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    manualReset.value = false
                    scope.launch {
                        delay(500)
                        startOne.floatValue = 0f

                        delay(150)
                        startTwo.floatValue = 0f
                        startThree.floatValue = 0f
                        startFour.floatValue = 0f
                    }
                }) {
                Text(text = "shimmer")
            }
        }
    }
}


@Composable
fun Cover(
    currentIndex: Int = 0,
    startHere: MutableState<Float>,
    duration: MutableState<Float> = remember { mutableFloatStateOf(1000f) },
    threshold: MutableState<Float> = remember { mutableFloatStateOf(0.97f) },
    manualReset: MutableState<Boolean>
) {


    var componentWidth by remember { mutableFloatStateOf(0f) }

    var animateTwo by remember { mutableFloatStateOf(1f) }
    var animateThree by remember { mutableFloatStateOf(1f) }
    var animateFour by remember { mutableFloatStateOf(1f) }
    var animateFive by remember { mutableFloatStateOf(1f) }

    var twoReset by rememberSaveable { mutableStateOf(false) }
    var threeReset by rememberSaveable { mutableStateOf(false) }
    var fourReset by rememberSaveable { mutableStateOf(false) }
    var fiveReset by rememberSaveable { mutableStateOf(false) }

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

    if (animateOneAlpha < threshold.value && !twoReset && !manualReset.value) {
        animateTwo = 0f
        twoReset = true
    }

    if (animateTwoAlpha < threshold.value && !threeReset && !manualReset.value) {
        animateThree = 0f
        threeReset = true
    }

    if (animateThreeAlpha < threshold.value && !fourReset && !manualReset.value) {
        animateFour = 0f
        fourReset = true
    }

    if (animateFour < threshold.value && !fiveReset && !manualReset.value) {
        animateFive = 0f
        fiveReset = true
    }


    val background = when (currentIndex) {
        0 -> Brush.radialGradient(
            0.0F to MaterialTheme.colorScheme.secondary.copy(animateOneAlpha),
            0.4F to MaterialTheme.colorScheme.secondary.copy(animateTwoAlpha),
            0.6F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            1F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            center = Offset(0f, 100f),
            radius = 900f,
            tileMode = TileMode.Decal
        )

        1 -> Brush.radialGradient(
            0.0F to MaterialTheme.colorScheme.secondary.copy(animateOneAlpha),
            0.4F to MaterialTheme.colorScheme.secondary.copy(animateTwoAlpha),
            0.6F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            1F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            center = Offset(-900f, 200f),
            radius = 3000f,
            tileMode = TileMode.Decal
        )

        2 -> Brush.radialGradient(
            0.0F to MaterialTheme.colorScheme.secondary.copy(animateOneAlpha),
            0.4F to MaterialTheme.colorScheme.secondary.copy(animateTwoAlpha),
            0.6F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            1F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            center = Offset(-0f, -500f),
            radius = 3000f,
            tileMode = TileMode.Decal
        )

        else -> Brush.radialGradient(
            0.0F to MaterialTheme.colorScheme.secondary.copy(animateOneAlpha),
            0.4F to MaterialTheme.colorScheme.secondary.copy(animateTwoAlpha),
            0.6F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            1F to MaterialTheme.colorScheme.secondary.copy(animateThreeAlpha),
            center = Offset(-900f, -900f),
            radius = 3000f,
            tileMode = TileMode.Decal
        )
    }


    if(manualReset.value){
        manualReset.value = true
        startHere.value = 1f
        animateTwo = 1f
        animateThree = 1f
        animateFour = 1f
        animateFive = 1f

        twoReset = false
        threeReset = false
        fourReset = false
        fiveReset = false
    }

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.size(200.dp, 300.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
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
                                            manualReset.value = true
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
                                            manualReset.value = false
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
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "This is some sample text here, this is soo cool!!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                0F to MaterialTheme.colorScheme.background.copy(animateOneAlpha),
                                0.25F to MaterialTheme.colorScheme.background.copy(animateTwoAlpha),
                                0.5F to MaterialTheme.colorScheme.background.copy(animateThreeAlpha),
                                0.75F to MaterialTheme.colorScheme.background.copy(animateFourAlpha),
                                1F to MaterialTheme.colorScheme.background.copy(animateFiveAlpha),
                            )
                        )
                )
            }
        }
    }
}
