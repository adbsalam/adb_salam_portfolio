package uk.adbsalam.portfolio.info.data

import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.network.genericException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class InfoRepository @Inject constructor(
    private val infoService: InfoService
) : InfoRepo {


    override suspend fun infographics(): Response<Infographics> {
        return try {
            val infographics = infoService.infographics(item = "infographics")
            Response.Success(data = infographics)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }

    override suspend fun workHistory(): Response<WorkHistory> {
        return try {
            val workHistory = infoService.workHistory(item = "workhistory")
            Response.Success(data = workHistory)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }

}