package uk.adbsalam.portfolio.home.feature.shimmerCarousal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerCardCarousal() {

    val shimmerCardRowState = remember { mutableStateOf(LazyRowShimmerState()) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ShimmerCardItem.mockCards().forEachIndexed { index, item ->
            item {
                ShimmerCard(
                    item = item,
                    currentIndex = index,
                    shimmerState = shimmerCardRowState
                )
            }
        }
    }
}


@Preview
@Composable
fun ShimmerCardPreview() {
    ShimmerCardCarousal()
}