package uk.adbsalam.portfolio.info.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Infographics(
    @Json(name = "infographics") val infoGraphics: List<Info>
) {
    @JsonClass(generateAdapter = true)
    data class Info(
        @Json(name = "name") val name: String,
        @Json(name = "value") val value: Float
    )

    companion object {
        fun createMock() = Infographics(
            listOf(
                Info(name = "Coroutine", value = 1f),
                Info(name = "CI/CD", value = 0.9f),
                Info(name = "REST", value = 1f),
                Info(name = "Unit Testing", value = 1f),
                Info(name = "E2E Testing", value = 0.8f),
                Info(name = "Modularisation", value = 0.8f),
                Info(name = "Flows", value = 0.9f),
                Info(name = "Flutter", value = 0.6f),
                Info(name = "SwiftUI", value = 0.7f),
            )
        )
    }
}