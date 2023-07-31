package uk.adbsalam.portfolio.data

import uk.adbsalam.portfolio.data.objects.HomeItems
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.network.genericException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class HomeRepository @Inject constructor(
    private val homeService: HomeService
) : HomeRepo {

    override suspend fun homeItems(): Response<HomeItems> {
        return try {
            val homeItems = homeService.homeItems("18G0K0zq7paLmYTueZiK74YbEzrrALjO5R8rRHKp", item = "home")

            Response.Success(data = homeItems)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }
}