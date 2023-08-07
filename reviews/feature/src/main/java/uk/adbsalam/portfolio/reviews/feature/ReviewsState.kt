package uk.adbsalam.portfolio.reviews.feature

import uk.adbsalam.portfolio.reviews.data.ReviewsData

sealed class ReviewsState {
    object OnLoading : ReviewsState()
    data class OnReviews(val reviews: List<ReviewsData>) : ReviewsState()
}