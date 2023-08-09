package uk.adbsalam.portfolio.videos.feature

import uk.adbsalam.portfolio.videos.data.objects.VideoItems

internal sealed class VideosState {
    object OnLoading : VideosState()
    data class OnError(val msg: String): VideosState()
    data class OnVideos(val videos: VideoItems) : VideosState()
}