package uk.adbsalam.portfolio.info.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkHistory(
    @Json(name = "workHistory") val workHistory: List<Work>
) {
    @JsonClass(generateAdapter = true)
    data class Work(
        @Json(name = "company") val company: String,
        @Json(name = "duration") val duration: String,
        @Json(name = "color") val color: String,
        @Json(name = "description") val description: String,
        @Json(name = "tags") val tags: List<WorkTag>
    ) {
        @JsonClass(generateAdapter = true)
        data class WorkTag(
            @Json(name = "tag") val tag: String,
            @Json(name = "icon") val icon: String
        )
    }

    companion object {
        fun createMock() = WorkHistory(
            workHistory = listOf(
                Work(
                    company = "NBrown Group Plc",
                    duration = "(2021 - Current)",
                    description = "Lorem Ipsum is simply dummy text of the ",
                    color = "orange",
                    tags = listOf(
                        Work.WorkTag(tag = "Android", icon = "ic_android"),
                        Work.WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                        Work.WorkTag(tag = "Compose", icon = "ic_compose"),
                        Work.WorkTag(tag = "E-Commerce", icon = "ic_shop"),
                        Work.WorkTag(tag = "Firebase", icon = "ic_firebase"),
                    ),
                ),
                Work(
                    company = "Sagoss Group",
                    duration = "(2020 - 2021)",
                    description = "Lorem Ipsum is simply dummy text of the",
                    color = "purple",
                    tags = listOf(
                        Work.WorkTag(tag = "Android", icon = "ic_android"),
                        Work.WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                        Work.WorkTag(tag = "BitBucket", icon = "ic_bitbucket"),
                        Work.WorkTag(tag = "Firebase", icon = "ic_firebase"),
                    ),
                ),
            )
        )
    }
}