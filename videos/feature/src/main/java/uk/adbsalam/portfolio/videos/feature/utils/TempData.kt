package uk.adbsalam.portfolio.videos.feature.utils

data class VideoData(
    val title: String,
    val description: String,
    val videoId: String
) {
    companion object {
        fun createMock() = listOf(
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "DE5o92ya25I"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "OFqTWt4OnLE"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "MS-CiBqGpbg"
            ),
            VideoData(
                title = "Test Title",
                description = "lorum ipsum somehting to show as description",
                videoId = "rYc5O8mSuF4"
            )
        )
    }
}