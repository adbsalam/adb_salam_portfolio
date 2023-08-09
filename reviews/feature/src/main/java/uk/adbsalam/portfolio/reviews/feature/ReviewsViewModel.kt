package uk.adbsalam.portfolio.reviews.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.reviews.data.ReviewsRepo
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val reviewsRepo: ReviewsRepo
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<ReviewsState>(ReviewsState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * Load all reviews data and start screen
     */
    internal suspend fun initReviews() {
        fetchReviews()
    }

    internal fun fetchReviews() {
        _viewState.value = ReviewsState.OnLoading

        viewModelScope.launch {

            when (val result = reviewsRepo.reviews()) {
                is Response.Failure -> {
                    _viewState.value =
                        ReviewsState.OnError("something went wrong, please try again")
                }

                is Response.Success -> {
                    _viewState.value = ReviewsState.OnReviews(result.data)
                }
            }
        }
    }
}