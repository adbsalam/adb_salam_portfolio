package uk.adbsalam.portfolio.info.data


data class InfoGraphic(
    val name: String,
    val value: Float
)

val infoList = listOf(
    InfoGraphic(
        name = "Coroutine",
        value = 1f
    ),
    InfoGraphic(
        name = "CI/CD",
        value = 0.9f
    ),
    InfoGraphic(
        name = "REST",
        value = 1f
    ),
    InfoGraphic(
        name = "Unit Testing",
        value = 1f
    ),
    InfoGraphic(
        name = "E2E Testing",
        value = 0.8f
    ),
    InfoGraphic(
        name = "Modularisation",
        value = 0.8f
    ),
    InfoGraphic(
        name = "Flows",
        value = 0.9f
    ),
    InfoGraphic(
        name = "Flutter",
        value = 0.6f
    ),
    InfoGraphic(
        name = "SwiftUI",
        value = 0.7f
    ),
    InfoGraphic(
        name = "Bash Shell",
        value = 0.6f
    ),
    InfoGraphic(
        name = "Docker",
        value = 0.5f
    ),
    InfoGraphic(
        name = "c++",
        value = 0.4f
    ),
    InfoGraphic(
        name = "AWS",
        value = 0.4f
    ),
)


data class WorkTag(
    val tag: String,
    val icon: String
)

data class WorkHistory(
    val company: String,
    val duration: String,
    val color: Long,
    val tags: List<WorkTag>,
    val description: String,
) {

    companion object {
        fun createMock() = listOf(
            WorkHistory(
                company = "NBrown Group Plc",
                duration = "(2021 - Current)",
                color = 0xFFFF9800,
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = "ic_android"
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = "ic_kotlin"
                    ),
                    WorkTag(
                        tag = "Compose",
                        icon = "ic_compose"
                    ),
                    WorkTag(
                        tag = "E-Commerce",
                        icon = "ic_shop"
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = "ic_firebase"
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "Sagoss Group",
                duration = "(2020 - 2021)",
                color = 0xFF9C27B0,
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = "ic_android"
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = "ic_kotlin"
                    ),
                    WorkTag(
                        tag = "BitBucket",
                        icon = "ic_bitbucket"
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = "ic_firebase"
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2019 - 2020)",
                company = "Payzone Bill Payments",
                color = 0xFFFF4785,
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = "ic_android"
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = "ic_kotlin"
                    ),
                    WorkTag(
                        tag = "Java",
                        icon = "ic_java"
                    ),
                    WorkTag(
                        tag = "C++",
                        icon = "ic_cplus"
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = "ic_firebase"
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2017 - 2019)",
                company = "Free Lancing",
                color = 0xFF4CAF50,
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = "ic_android"
                    ),
                    WorkTag(
                        tag = "Java",
                        icon = "ic_java"
                    ),
                    WorkTag(
                        tag = "WordPress",
                        icon = "ic_wordpress"
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = "ic_firebase"
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
        )
    }

}
