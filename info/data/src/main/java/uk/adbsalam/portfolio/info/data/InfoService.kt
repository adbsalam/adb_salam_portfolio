package uk.adbsalam.portfolio.info.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory

internal interface InfoService {

    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "infographics" to retrieve home items
     */
    @GET("portfolio")
    suspend fun infographics(@Query("item") item: String): Infographics


    /**
     * @param item item value to retrieve from API
     * For Home Items item value will be "infographics" to retrieve home items
     */
    @GET("portfolio")
    suspend fun workHistory(@Query("item") item: String): WorkHistory
}