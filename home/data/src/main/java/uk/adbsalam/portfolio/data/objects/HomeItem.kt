package uk.adbsalam.portfolio.data.objects

import androidx.compose.runtime.Stable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.annotation.concurrent.Immutable

@Immutable
@Stable
@JsonClass(generateAdapter = true)
data class HomeItems(
    @field:Json(name = "home") val home: List<HomeItem>,
) {
    @JsonClass(generateAdapter = true)
    data class HomeItem(
        @field:Json(name = "tags") val tags: List<String>,
        @field:Json(name = "title") val title: String,
        @field:Json(name = "type") val type: String,
        @field:Json(name = "res") val res: String,
        @field:Json(name = "body") val body: String,
        @field:Json(name = "deeplink") val deeplink: String,
    )
}
