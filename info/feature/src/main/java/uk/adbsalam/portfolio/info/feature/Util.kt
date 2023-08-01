package uk.adbsalam.portfolio.info.feature

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import uk.adbsalam.portfolio.components.R

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


data class WorkHistory(
    val company: String,
    val duration: String,
    @DrawableRes val logo: Int,
    val description: String,
) {

    companion object {

        fun createMock() = listOf(
            WorkHistory(
                company = "NBrown Group Plc",
                duration = "(2021 - Current)",
                logo = R.drawable.ic_nbrown,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "Sagoss Group",
                duration = "(2020 - 2021)",
                logo = R.drawable.ic_sagoss,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2019 - 2020)",
                company = "Payzone Bill Payments",
                logo = R.drawable.ic_payzone_logo,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2017 - 2019)",
                company = "Free Lancing",
                logo = 0,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
        )
    }

}
