package uk.adbsalam.portfolio.home.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Cover() {
    var animateOne by remember { mutableFloatStateOf(1f) }
    var animateTwo by remember { mutableFloatStateOf(1f) }
    var animateThree by remember { mutableFloatStateOf(1f) }
    var animateFour by remember { mutableFloatStateOf(1f) }
    var animateFive by remember { mutableFloatStateOf(1f) }
    var twoReset by rememberSaveable { mutableStateOf(false) }
    var threeReset by rememberSaveable { mutableStateOf(false) }
    var fourReset by rememberSaveable { mutableStateOf(false) }
    var fiveReset by rememberSaveable { mutableStateOf(false) }


    val animateOneAlpha by animateFloatAsState(
        targetValue = animateOne,
        label = "",
        animationSpec = tween(2000),
    )

    val animateTwoAlpha by animateFloatAsState(
        targetValue = animateTwo,
        label = "",
        animationSpec = tween(2000)
    )

    val animateThreeAlpha by animateFloatAsState(
        targetValue = animateThree,
        label = "",
        animationSpec = tween(2000),
    )

    val animateFourAlpha by animateFloatAsState(
        targetValue = animateFour,
        label = "",
        animationSpec = tween(2000),
    )

    val animateFiveAlpha by animateFloatAsState(
        targetValue = animateFive,
        label = "",
        animationSpec = tween(2000),
    )

    if(animateOneAlpha < 0.5 && !twoReset){
        animateTwo = 0f
        twoReset = true
    }

    if(animateTwoAlpha < 0.5 && !threeReset){
        animateThree = 0f
        threeReset = true
    }

    if(animateThreeAlpha < 0.5 && !fourReset){
        animateFour = 0f
        fourReset = true
    }

    if(animateFourAlpha < 0.5 && !fiveReset){
        animateFive = 0f
        fiveReset = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.size(240.dp, 300.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(modifier = Modifier.height(200.dp).fillMaxWidth()){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = uk.adbsalam.portfolio.components.R.drawable.swiss_8),
                    contentDescription = null
                )


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            animateOne = if (animateOne == 0f) 1f else 0f
                            animateTwo = if (animateOne == 0f) 1f else 0f
                            animateThree = if (animateOne == 0f) 1f else 0f
                            animateFour = if (animateOne == 0f) 1f else 0f
                            animateFive = if (animateOne == 0f) 1f else 0f

                        }
                        .background(
                            Brush.linearGradient(
                                0F to Color.Red.copy(animateOneAlpha),
                                0.25F to Color.Red.copy(animateTwoAlpha),
                                0.5F to Color.Red.copy(animateThreeAlpha),
                                0.75F to Color.Red.copy(animateFourAlpha),
                                1F to Color.Red.copy(animateFiveAlpha),
                            )
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun ComicsPreview() {
    Cover()
}