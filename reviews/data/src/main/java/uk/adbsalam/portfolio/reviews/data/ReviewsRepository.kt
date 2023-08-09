package uk.adbsalam.portfolio.reviews.data

import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.network.genericException
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ReviewsRepository @Inject constructor(
    private val reviewsService: ReviewsService
) : ReviewsRepo {

    override suspend fun reviews(): Response<ReviewItems> {
        return try {
            val reviews = reviewsService.reviews(item = "reviews")
            Response.Success(data = reviews)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }
}