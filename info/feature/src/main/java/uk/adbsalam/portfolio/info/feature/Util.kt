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
    val iconTint: Color,
    val tags: List<WorkTag>,
    val description: String,
) {
    companion object {

        fun createMock() = listOf(
            WorkHistory(
                company = "How can I pay for my credit payments",
                iconTint = Color(0xFFFF9800),
                tags = listOf(
                    WorkTag(
                        tag = "GPay",
                        icon = R.drawable.ic_google
                    ),
                    WorkTag(
                        tag = "Card",
                        icon = R.drawable.ic_card
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "How can apply for more credit?",
                iconTint = Color(0xFF9C27B0),
                tags = listOf(
                    WorkTag(
                        tag = "Website",
                        icon = R.drawable.ic_web
                    ),
                    WorkTag(
                        tag = "App",
                        icon = R.drawable.ic_android
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "What if i do not pay my min payment",
                iconTint = Color(0xFFFF4785),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Website",
                        icon = R.drawable.ic_web
                    ),
                    WorkTag(
                        tag = "Phone",
                        icon = R.drawable.ic_phone
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "Where can I check how much credit I have left",
                iconTint = Color(0xFF4CAF50),
                tags = listOf(
                    WorkTag(
                        tag = "Android",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Website",
                        icon = R.drawable.ic_web
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
        )
    }

}
