package uk.adbsalam.portfolio.blog.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlogResponse(
    @Json(name = "title")
    val title: String,
    @Json(name = "subTitle")
    val subTitle: String,
    @Json(name = "components")
    val components: List<Component>
) {
    @JsonClass(generateAdapter = true)
    data class Component(
        @Json(name = "type")
        val type: String,
        @Json(name = "data")
        val data: String
    )
}