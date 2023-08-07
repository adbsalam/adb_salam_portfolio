package uk.adbsalam.portfolio.videos.feature

import uk.adbsalam.portfolio.videos.data.VideoData

internal sealed class VideosState {
    object OnLoading : VideosState()
    data class OnVideos(val videos: List<VideoData>) : VideosState()
}