package uk.adbsalam.portfolio.info.feature

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import uk.adbsalam.portfolio.components.R

data class InfoGraphic(
    val name: String,
    val value: Float
)

val infoList = listOf(
    InfoGraphic(
        name = "Makeup",
        value = 0.4f
    ),
    InfoGraphic(
        name = "Jumpers",
        value = 0.6f
    ),
    InfoGraphic(
        name = "Jackets",
        value = 0.2f
    ),
    InfoGraphic(
        name = "Dresses",
        value = 0.4f
    ),
    InfoGraphic(
        name = "Home",
        value = 0.3f
    ),
    InfoGraphic(
        name = "Shirts",
        value = 0.8f
    ),
    InfoGraphic(
        name = "Bags",
        value = 0.4f
    ),
    InfoGraphic(
        name = "Health",
        value = 0.2f
    ),
    InfoGraphic(
        name = "Gloves",
        value = 0.5f
    ),
    InfoGraphic(
        name = "Tech",
        value = 0.9f
    ),
    InfoGraphic(
        name = "Cables",
        value = 0.1f
    ),
    InfoGraphic(
        name = "others",
        value = 0.1f
    ),
)

data class WorkTag(
    val tag: String,
    @DrawableRes val icon: Int
)

data class WorkHistory(
    val company: String,
    val duration: String,
    @DrawableRes val logo: Int,
    val iconTint: Color,
    val tags: List<WorkTag>,
    val description: String,
) {
    companion object {

        fun createMock() = listOf(
            WorkHistory(
                company = "NBrown Group Plc",
                duration = "(2021 - Current)",
                logo = R.drawable.ic_nbrown,
                iconTint = Color(0xFFFF9800),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = R.drawable.ic_kotlin
                    ),
                    WorkTag(
                        tag = "Compose",
                        icon = R.drawable.ic_compose_logo
                    ),
                    WorkTag(
                        tag = "E-Commerce",
                        icon = R.drawable.ic_shop
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = R.drawable.ic_firebase
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "Sagoss Group",
                duration = "(2020 - 2021)",
                logo = R.drawable.ic_sagoss,
                iconTint = Color(0xFF9C27B0),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = R.drawable.ic_kotlin
                    ),
                    WorkTag(
                        tag = "BitBucket",
                        icon = R.drawable.ic_bit_bucket
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = R.drawable.ic_firebase
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2019 - 2020)",
                company = "Payzone Bill Payments",
                logo = R.drawable.ic_payzone_logo,
                iconTint = Color(0xFFFF4785),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Kotlin",
                        icon = R.drawable.ic_kotlin
                    ),
                    WorkTag(
                        tag = "Java",
                        icon = R.drawable.ic_java
                    ),
                    WorkTag(
                        tag = "C++",
                        icon = R.drawable.ic_c_plus
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = R.drawable.ic_firebase
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                duration = "(2017 - 2019)",
                company = "Free Lancing",
                logo = R.drawable.ic_git,
                iconTint = Color(0xFF4CAF50),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Java",
                        icon = R.drawable.ic_java
                    ),
                    WorkTag(
                        tag = "WordPress",
                        icon = R.drawable.ic_wordpress
                    ),
                    WorkTag(
                        tag = "Firebase",
                        icon = R.drawable.ic_firebase
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
        )
    }

}
