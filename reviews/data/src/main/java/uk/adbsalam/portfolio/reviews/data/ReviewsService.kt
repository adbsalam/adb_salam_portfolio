package uk.adbsalam.portfolio.reviews.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems

internal fun interface ReviewsService {

    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "reviews" to retrieve home items
     */
    @GET("portfolio")
    suspend fun reviews(@Query("item") item: String): ReviewItems
}