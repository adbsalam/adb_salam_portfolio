package uk.adbsalam.portfolio.blog.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.adbsalam.portfolio.blog.data.objects.BlogResponse

internal fun interface BlogService {

    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "home" to retrieve home items
     */
    @GET("portfolio")
    suspend fun blogItems(@Query("item") item: String): BlogResponse
}