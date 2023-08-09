package uk.adbsalam.portfolio.videos.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.adbsalam.portfolio.videos.data.objects.VideoItems

internal fun interface VideoService {

    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "videos" to retrieve home items
     */
    @GET("portfolio")
    suspend fun videos(@Query("item") item: String): VideoItems
}