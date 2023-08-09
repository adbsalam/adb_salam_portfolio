package uk.adbsalam.portfolio.reviews.feature

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uk.adbsalam.portfolio.components.AnimatedColumn
import uk.adbsalam.portfolio.components.ErrorPage
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme

/**
 * @param viewModel view model to be used for this screen
 *
 * This is entry point to Reviews screen
 * Perform all functionality that might block Previews
 */
@Composable
fun Reviews(
    viewModel: ReviewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()
    LaunchedEffect(key1 = null) { viewModel.initReviews() }
    Reviews(
        uiState = uiState,
        retry = viewModel::fetchReviews
    )
}

/**
 * @param uiState Current UI State to show on screen
 * Changes state based on uiState such as loading or show reviews
 */
@Composable
private fun Reviews(
    uiState: ReviewsState,
    retry: () -> Unit
) {
    when (uiState) {
        ReviewsState.OnLoading -> LoadingLotti()

        is ReviewsState.OnError -> {
            ErrorPage(
                msg = uiState.msg,
                retry = retry
            )
        }

        is ReviewsState.OnReviews -> {
            AnimatedColumn {
                ReviewsScreen(reviews = uiState.reviews)
            }
        }
    }
}

@Preview
@Composable
internal fun ReviewsPreviewLight() {
    Adb_Screen_Theme {
        Reviews(
            uiState = ReviewsState.OnReviews(ReviewItems.createMock()),
            retry = { /*unused*/ }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun ReviewsPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        Reviews(
            uiState = ReviewsState.OnReviews(ReviewItems.createMock()),
            retry = { /*unused*/ }
        )
    }
}