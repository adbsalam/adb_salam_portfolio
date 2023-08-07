package uk.adbsalam.portfolio.reviews.feature

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
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
            AnimatedColumn {
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