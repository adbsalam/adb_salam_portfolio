package uk.adbsalam.portfolio.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uk.adbsalam.portfolio.data.objects.HomeItems

internal fun interface HomeService {

    @GET("portfolio")
    suspend fun homeItems(
        @Header("x-api-key") apiKey: String,
        @Query("item") item: String
    ): HomeItems

}