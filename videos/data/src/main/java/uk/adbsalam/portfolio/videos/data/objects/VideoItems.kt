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
                    title = "Switzerland travel diary",
                    description = "Exploring some of the most beautiful places in Switzerland. Capturing most amazing views and location, if you would like to explore Switzerland someday, this video is for you",
                    videoId = "DE5o92ya25I"
                ),
                Video(
                    title = "Croatia - Game of Thrones trip!",
                    description = "Trip to Croatia from Splits to Dubrovnik in search for all shooting locations of game of thrones. Feast your eyes with some stunning views from Croatia",
                    videoId = "OFqTWt4OnLE"
                ),
                Video(
                    title = "Snowdonia - Day Ride",
                    description = "Having a day trip for bike ride across Snowdonia. A hike to Jublee tower, a drive to Penn Y Pass and beautiful views of Swallow Falls",
                    videoId = "MS-CiBqGpbg"
                ),
                Video(
                    title = "Bamford's Edge",
                    description = "If you love Motorcycle rides, this route will be one of the best to explore in UK. Lovely ride from SnakePass that ends with an amazing hike to Bamford's edge.",
                    videoId = "rYc5O8mSuF4"
                )
            )
        )
    }
}
