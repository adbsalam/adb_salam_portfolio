package uk.adbsalam.portfolio.videos.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoItems(
    @Json(name = "videos") val videos: List<Video>
) {
    @JsonClass(generateAdapter = true)
    data class Video(
        @Json(name = "title") val title: String,
        @Json(name = "description") val description: String,
        @Json(name = "videoId") val videoId: String
    )

    companion object {
        fun createMock() = VideoItems(
            listOf(
                Video(
                    title = "Test Title",
                    description = "lorum ipsum somehting to show as description",
                    videoId = "DE5o92ya25I"
                ),
                Video(
                    title = "Test Title",
                    description = "lorum ipsum somehting to show as description",
                    videoId = "OFqTWt4OnLE"
                ),
                Video(
                    title = "Test Title",
                    description = "lorum ipsum somehting to show as description",
                    videoId = "MS-CiBqGpbg"
                ),
                Video(
                    title = "Test Title",
                    description = "lorum ipsum somehting to show as description",
                    videoId = "rYc5O8mSuF4"
                )
            )
        )
    }
}
