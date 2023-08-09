package uk.adbsalam.portfolio.reviews.feature

import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems

sealed class ReviewsState {
    object OnLoading : ReviewsState()
    data class OnError(val msg: String): ReviewsState()
    data class OnReviews(val reviews: ReviewItems) : ReviewsState()
}