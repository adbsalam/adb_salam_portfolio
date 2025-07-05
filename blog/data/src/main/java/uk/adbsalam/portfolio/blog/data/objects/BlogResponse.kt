package uk.adbsalam.portfolio.blog.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlogResponse(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "subTitle")
    val subTitle: String,
    @field:Json(name = "components")
    val components: List<Component>,
) {
    @JsonClass(generateAdapter = true)
    data class Component(
        @field:Json(name = "type")
        val type: String,
        @field:Json(name = "data")
        val data: String,
    )
}
