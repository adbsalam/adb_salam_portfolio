package uk.adbsalam.portfolio.info.data

import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory
import uk.adbsalam.portfolio.network.Response

/**
 * Info Repo interface to expose to required modules
 */
interface InfoRepo {
    suspend fun infographics(): Response<Infographics>
    suspend fun workHistory(): Response<WorkHistory>
}