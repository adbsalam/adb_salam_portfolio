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

    var twoReset by rememberSaveable { mutableStateOf(false) }
    var threeReset by rememberSaveable { mutableStateOf(false) }

    val animateOneAlpha by animateFloatAsState(
        targetValue = animateOne,
        label = "",
        animationSpec = tween(1000),
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

    if(animateOneAlpha < 0.8 && !twoReset){
        animateTwo = 0f
        twoReset = true
    }

    if(animateTwoAlpha < 0.8 && !threeReset){
        animateThree = 0f
        threeReset = true
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
                        }
                        .background(
                            Brush.linearGradient(
                                0F to Color.Red.copy(animateOneAlpha),
                                0.5F to Color.Red.copy(animateTwoAlpha),
                                1F to Color.Red.copy(animateThreeAlpha),
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