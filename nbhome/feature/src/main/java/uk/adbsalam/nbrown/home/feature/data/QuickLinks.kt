package uk.adbsalam.nbrown.home.feature.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import uk.adbsalam.portfolio.components.R


data class WorkTag(
    val tag: String,
    @DrawableRes val icon: Int
)

data class WorkHistory(
    val company: String,
    @DrawableRes val logo: Int,
    val iconTint: Color,
    val tags: List<WorkTag>,
    val description: String,
) {
    companion object {

        fun createMock() = listOf(
            WorkHistory(
                company = "How do i pay?",
                logo = R.drawable.ic_nbrown,
                iconTint = Color(0xFFFF9800),
                tags = listOf(
                    WorkTag(
                        tag = "Card",
                        icon = R.drawable.ic_card
                    ),
                    WorkTag(
                        tag = "GPay",
                        icon = R.drawable.ic_google
                    ),
                    WorkTag(
                        tag = "Credit",
                        icon = R.drawable.ic_credit
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "How to apply for credit account?",
                logo = R.drawable.ic_sagoss,
                iconTint = Color(0xFF9C27B0),
                tags = listOf(
                    WorkTag(
                        tag = "App",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Website",
                        icon = R.drawable.ic_web
                    ),
                ),
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            ),
            WorkHistory(
                company = "How do I contact help desk?",
                logo = R.drawable.ic_payzone_logo,
                iconTint = Color(0xFFFF4785),
                tags = listOf(
                    WorkTag(
                        tag = "App",
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
                company = "How do I arrange a return",
                logo = R.drawable.ic_git,
                iconTint = Color(0xFF4CAF50),
                tags = listOf(
                    WorkTag(
                        tag = "App",
                        icon = R.drawable.ic_android
                    ),
                    WorkTag(
                        tag = "Phone",
                        icon = R.drawable.ic_phone
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
