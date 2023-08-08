package uk.adbsalam.portfolio.reviews.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.adbsalam.portfolio.reviews.data.ReviewsData
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor() : ViewModel() {

    private val _viewState =
        MutableStateFlow<ReviewsState>(ReviewsState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * Load all reviews data and start screen
     */
    internal suspend fun initReviews() {
        _viewState.value = ReviewsState.OnReviews(ReviewsData.createMock())
    }

}