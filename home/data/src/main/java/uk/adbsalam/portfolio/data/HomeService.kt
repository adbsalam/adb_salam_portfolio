package uk.adbsalam.portfolio.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.adbsalam.portfolio.data.objects.HomeItems

internal fun interface HomeService {

    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "home" to retrieve home items
     */
    @GET("portfolio")
    suspend fun homeItems(@Query("item") item: String): HomeItems
}