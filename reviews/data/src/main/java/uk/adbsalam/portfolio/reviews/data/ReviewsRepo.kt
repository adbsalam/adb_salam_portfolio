package uk.adbsalam.portfolio.reviews.data

import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems


/**
 * Reviews Repo interface to expose to required modules
 */
interface ReviewsRepo {
    suspend fun reviews(): Response<ReviewItems>
}