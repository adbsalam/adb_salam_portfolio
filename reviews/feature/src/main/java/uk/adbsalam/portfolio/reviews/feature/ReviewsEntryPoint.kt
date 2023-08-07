package uk.adbsalam.portfolio.reviews.feature

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.reviews.data.ReviewsData
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun Reviews(
    viewModel: ReviewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.initReviews()
    }

    Reviews(
        uiState = uiState
    )
}

@Composable
fun Reviews(
    uiState: ReviewsState
) {
    when (uiState) {
        ReviewsState.OnLoading -> LoadingLotti()
        is ReviewsState.OnReviews -> {
            var visibility by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = null) {
                visibility = true
            }

            AnimatedVisibility(
                visible = visibility,
                enter = fadeIn(tween(500))
            ) {
                ReviewsScreen(reviews = uiState.reviews)
            }
        }
    }
}

@Preview
@Composable
fun ReviewsPreviewLight() {
    Adb_Theme {
        Reviews(
            uiState = ReviewsState.OnReviews(ReviewsData.createMock())
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReviewsPreviewDark() {
    Adb_Theme(true) {
        Reviews(
            uiState = ReviewsState.OnReviews(ReviewsData.createMock())
        )
    }
}