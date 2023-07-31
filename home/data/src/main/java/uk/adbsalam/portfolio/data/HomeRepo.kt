package uk.adbsalam.portfolio.data

import uk.adbsalam.portfolio.data.objects.HomeItems
import uk.adbsalam.portfolio.network.Response

interface HomeRepo {
    suspend fun homeItems(): Response<HomeItems>
}