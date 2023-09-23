package uk.adbsalam.portfolio.components.shimmerCarousal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ShimmerCardCarousal(
    viewModel: ShimmerViewModel = hiltViewModel()
) {
    val shimmerState by viewModel.shimmerState.collectAsState()

    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ShimmerCardItem.mockCards().forEachIndexed { index, item ->
            item {
                ShimmerCard(
                    item = item,
                    currentIndex = index,
                    shimmerState = shimmerState,
                    onUpdateState = viewModel::updateState
                )
            }
        }
    }
}

//
//@Preview
//@Composable
//fun ShimmerCardPreview() {
//    ShimmerCardCarousal(
//        shimmerCardRowState = remember { mutableStateOf(LazyRowShimmerState()) }
//    )
//}