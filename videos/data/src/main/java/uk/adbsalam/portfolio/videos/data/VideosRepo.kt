package uk.adbsalam.portfolio.videos.data

import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.videos.data.objects.VideoItems

/**
 * Home Repo interface to expose to required modules
 */
interface VideosRepo {
    suspend fun videos(): Response<VideoItems>
}