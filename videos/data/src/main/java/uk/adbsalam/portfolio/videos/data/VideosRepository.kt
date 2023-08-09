package uk.adbsalam.portfolio.videos.data

import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.network.genericException
import uk.adbsalam.portfolio.videos.data.objects.VideoItems
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class VideosRepository @Inject constructor(
    private val videosService: VideoService
) : VideosRepo {

    override suspend fun videos(): Response<VideoItems> {
        return try {
            val videos = videosService.videos(item = "videos")
            Response.Success(data = videos)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }

}