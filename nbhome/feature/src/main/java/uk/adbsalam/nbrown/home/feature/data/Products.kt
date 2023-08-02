package uk.adbsalam.nbrown.home.feature.data

import androidx.annotation.DrawableRes
import uk.adbsalam.portfolio.components.R

data class Product(
    val price: String,
    val title: String,
    @DrawableRes val image: Int
) {

    companion object {
        fun createData() = listOf(
            Product(
                price = "£19.99",
                title = "Floral dress night",
                image = R.drawable.ic_image_1
            ),
            Product(
                price = "£23.33",
                title = "BMW trousers range",
                image = R.drawable.ic_image_2
            ),
            Product(
                price = "£32.00",
                title = "Unagi jacket for women",
                image = R.drawable.ic_image_3
            ),
            Product(
                price = "21.11",
                title = "Calming color dress",
                image = R.drawable.ic_image_4
            ),
            Product(
                price = "£11.00",
                title = "Latest gaming dress",
                image = R.drawable.ic_image_5
            ),
            Product(
                price = "£29.00",
                title = "Floral dress night",
                image = R.drawable.ic_image_6
            )

        )
    }
}
