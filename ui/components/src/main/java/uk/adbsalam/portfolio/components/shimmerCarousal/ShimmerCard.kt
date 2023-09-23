package uk.adbsalam.portfolio.components.shimmerCarousal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun ShimmerCard(
    item: ShimmerCardItem,
    currentIndex: Int,
    shimmerState: LazyRowShimmerState,
    onUpdateState: (LazyRowShimmerState.LazyRowItemShimmer) -> Unit,
) {
    val isShimmerComplete =
        shimmerState.stateList.firstOrNull { it.index == currentIndex }?.isShimmerComplete
            ?: false

    var animOne by remember { mutableFloatStateOf(if (isShimmerComplete) 0f else 1f) }
    var animateTwo by remember { mutableFloatStateOf(if (isShimmerComplete) 0f else 1f) }
    var animateThree by remember { mutableFloatStateOf(if (isShimmerComplete) 0f else 1f) }

    val animateOneAlpha by rememberShimmerAnimateAsFloat(animOne)
    val animTwoAlpha by rememberShimmerAnimateAsFloat(animateTwo)
    val animThreeAlpha by rememberShimmerAnimateAsFloat(animateThree)
    var twoReset by rememberSaveable { mutableStateOf(isShimmerComplete) }
    var threeReset by rememberSaveable { mutableStateOf(isShimmerComplete) }

    LaunchedEffect(key1 = null, block = {
        if (!isShimmerComplete) {
            delay(1000)
            if (currentIndex > 0) {
                delay(150)
            } //extra delay to let first anim start first
            animOne = 0f

            onUpdateState(
                LazyRowShimmerState.LazyRowItemShimmer(
                    index = currentIndex,
                    isShimmerComplete = true
                )
            )
        }
    })

    if (animateOneAlpha < SHIMMER_GRADIENTS_THRESHOLD && !twoReset) {
        animateTwo = 0f
        twoReset = true
    }

    if (animTwoAlpha < SHIMMER_GRADIENTS_THRESHOLD && !threeReset) {
        animateThree = 0f
        threeReset = true
    }

    Card(
        modifier = Modifier.size(200.dp, 300.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column {

            ShimmerCardImage(
                item = item,
                alphaOne = animateOneAlpha,
                alphaTwo = animTwoAlpha,
                alphaThree = animThreeAlpha,
                index = currentIndex
            )

            ShimmerCardText(
                item = item,
                alphaOne = animateOneAlpha,
                alphaTwo = animTwoAlpha,
                alphaThree = animThreeAlpha
            )
        }
    }
}

@Composable
fun ShimmerCardImage(
    item: ShimmerCardItem,
    alphaOne: Float,
    alphaTwo: Float,
    alphaThree: Float,
    index: Int
) {
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
            painter = painterResource(id = item.imageRes),
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {}
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                .background(shimmerRadialBackground(alphaOne, alphaTwo, alphaThree, index))
        )
    }
}

@Composable
fun ShimmerCardText(
    item: ShimmerCardItem,
    alphaOne: Float,
    alphaTwo: Float,
    alphaThree: Float,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = item.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(shimmerTextBackground(alphaOne, alphaTwo, alphaThree))
        )
    }
}



